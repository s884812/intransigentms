package net.sf.odinms.server.life;

import net.sf.odinms.tools.Pair;

import java.util.*;

public class MapleMonsterStats {
    private int exp;
    private int hp, mp;
    private int level;
    private int removeAfter;
    private boolean boss;
    private int PADamage;
    private boolean undead;
    private boolean ffaLoot;
    private String name;
    private final Map<String, Integer> animationTimes = new HashMap<>();
    private final Map<Element, ElementalEffectiveness> resistance = new HashMap<>();
    private List<Integer> revives = Collections.emptyList();
    private byte tagColor;
    private byte tagBgColor;
    private final List<Pair<Integer, Integer>> skills = new ArrayList<>();
    private boolean firstAttack;
    private int buffToGive;
    private boolean explosive;
    private int accuracy;
    private int avoid;

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getAccuracy() {
        return this.accuracy;
    }
    
    public void setAccuracy(int acc) {
        this.accuracy = acc;
    }
    
    public int getAvoid() {
        return this.avoid;
    }
    
    public void setAvoid(int avoid) {
        this.avoid = avoid;
    }

    public int getRemoveAfter() {
        return removeAfter;
    }

    public void setRemoveAfter(int removeAfter) {
        this.removeAfter = removeAfter;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setFfaLoot(boolean ffaLoot) {
        this.ffaLoot = ffaLoot;
    }

    public boolean isFfaLoot() {
        return ffaLoot;
    }

    public void setAnimationTime(String name, int delay) {
        animationTimes.put(name, delay);
    }

    public int getAnimationTime(String name) {
        Integer ret = animationTimes.get(name);
        if (ret == null) {
            return 500;
        }
        return ret;
    }

    public boolean isMobile() {
        return animationTimes.containsKey("move") || animationTimes.containsKey("fly");
    }

    public List<Integer> getRevives() {
        return revives;
    }

    public void setRevives(List<Integer> revives) {
        this.revives = revives;
    }

    public void setUndead(boolean undead) {
        this.undead = undead;
    }

    public boolean getUndead() {
        return undead;
    }

    public void setEffectiveness(Element e, ElementalEffectiveness ee) {
        resistance.put(e, ee);
    }

    public ElementalEffectiveness getEffectiveness(Element e) {
        ElementalEffectiveness elementalEffectiveness = resistance.get(e);
        if (elementalEffectiveness == null) {
            return ElementalEffectiveness.NORMAL;
        } else {
            return elementalEffectiveness;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getTagColor() {
        return tagColor;
    }

    public void setTagColor(int tagColor) {
        this.tagColor = (byte) tagColor;
    }

    public byte getTagBgColor() {
        return tagBgColor;
    }

    public void setTagBgColor(int tagBgColor) {
        this.tagBgColor = (byte) tagBgColor;
    }

    public void setSkills(List<Pair<Integer, Integer>> skills) {
        for (Pair<Integer, Integer> skill : skills) {
            this.skills.add(skill);
        }
    }

    public List<Pair<Integer, Integer>> getSkills() {
        return Collections.unmodifiableList(this.skills);
    }

    public int getNoSkills() {
        return this.skills.size();
    }

    public boolean hasSkill(int skillId, int level) {
        for (Pair<Integer, Integer> skill : skills) {
            if (skill.getLeft() == skillId && skill.getRight() == level) {
                return true;
            }
        }
        return false;
    }

    public void setFirstAttack(boolean firstAttack) {
        this.firstAttack = firstAttack;
    }

    public boolean isFirstAttack() {
        return firstAttack;
    }

    public void setBuffToGive(int buff) {
        this.buffToGive = buff;
    }

    public int getBuffToGive() {
        return buffToGive;
    }

    public void setPADamage(int dmg) {
        this.PADamage = dmg;
    }

    public int getPADamage() {
        return PADamage;
    }

    public void setExplosive(boolean explosive) {
        this.explosive = explosive;
    }

    public boolean isExplosive() {
        return explosive;
    }
}
