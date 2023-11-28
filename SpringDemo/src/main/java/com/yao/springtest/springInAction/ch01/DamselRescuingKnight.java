package com.yao.springtest.springInAction.ch01;

import org.junit.Test;

/**
 * demo in action
 *
 * @date: 2023-11-22
 * @author: yao
 */
public class DamselRescuingKnight implements Knight {
    private DameSelRescuingQuest quest;

    // 紧耦合
    public DamselRescuingKnight() {
        this.quest = new DameSelRescuingQuest();
    }

    public void embarkOnQuest() {
        quest.embark();
    }

    public static void main(String[] args) {
        BraveKnight braveKnight = new BraveKnight(new DameSelRescuingQuest());
        braveKnight.embarkOnQuest();
    }
}

// 松耦合
class BraveKnight implements Knight {
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }
}

interface Knight {
    void embarkOnQuest();
}

interface Quest {
    void embark();
}

class DameSelRescuingQuest implements Quest {

    public void embark() {
    }
}

class BraveKnightTest {
    @Test
    public void KnightShouldEmbarkOnQuest() {
//        Quest mockQuest = mock(Quest.class);
    }
}