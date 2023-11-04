package cn.net.nit.ems.domain;

/**
 * @author lyq
 * @time 2023/11/4 21:39
 */
public class Department {
    private int id;
    /** 部门名称 */
    private String name;
    /** 上一级部门 */
    private int parentId;
    /** 0：学校； 1：学院； 2：系/专业；3：班级 */
    private int level;
    /** 入学年份（当部门为班级层次时） */
    private String entranceYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(String entranceYear) {
        this.entranceYear = entranceYear;
    }
}