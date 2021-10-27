package com.yao.springtest.beanpostprocesstest.after.factory;

import com.yao.springtest.beanpostprocesstest.after.annotation.RoutingSwitch;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author xiao.K
 * @date 2021/10/27
 */
public class RoutingBeanProxyFactory {
    public static Object createProxy(Class targetClass, Map<String, Object> beans) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(targetClass);
        proxyFactory.addAdvice((Advice) new VersionRoutingMethodInterceptor(targetClass, beans));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private String classSwitch;
        private Object beanOfSwitchOn;
        private Object beanOfSwitchOff;

        public VersionRoutingMethodInterceptor(Class targetClass, Map<String, Object> beans) {
            String interfaceName = StringUtils.uncapitalize(targetClass.getSimpleName());
            if (targetClass.isAnnotationPresent(RoutingSwitch.class)) {
                this.classSwitch = ((RoutingSwitch) targetClass.getAnnotation(RoutingSwitch.class)).value();
            }
            this.beanOfSwitchOn = beans.get(this.buildBeanName(interfaceName, true));
            this.beanOfSwitchOff = beans.get(this.buildBeanName(interfaceName, false));
        }

        private String buildBeanName(String interfaceName, boolean isSwitchOn) {
            return interfaceName + "Impl" + (isSwitchOn ? "V2" : "V1");
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            String switchName = this.classSwitch;
            if (method.isAnnotationPresent(RoutingSwitch.class)) {
                switchName = method.getAnnotation(RoutingSwitch.class).value();
            }
            if (StringUtils.isBlank(switchName)) {
                throw new IllegalStateException("RoutingSwitch's value is blank, method:" + method.getName());
            }
            return invocation.getMethod().invoke(getTargetBean(switchName), invocation.getArguments());
        }

        public Object getTargetBean(String switchName) {
            boolean switchOn;
            if (RoutingVersion.A.equals(switchName)) {
                switchOn = false;
            } else if (RoutingVersion.B.equals(switchName)) {
                switchOn = true;
            } else {
                switchOn = FunctionSwitch.isSwitchOpened(switchName);
            }
            return switchOn ? beanOfSwitchOn : beanOfSwitchOff;
        }
    }

    private static class FunctionSwitch {
        public static Boolean isSwitchOpened(String switchName) {
            return false;
        }
    }
}

@AllArgsConstructor
@NoArgsConstructor
enum RoutingVersion {
    A("A"),
    B("B");
    public String K;

    public String getK() {
        return K;
    }

    public void setK(String k) {
        K = k;
    }
}
