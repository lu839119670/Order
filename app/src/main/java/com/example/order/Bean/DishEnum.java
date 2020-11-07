package com.example.order.Bean;

/**
 * 一个用于区分菜品分类的枚举
 */
public enum DishEnum {
    /**
     * 主菜
     */
    ENTREE("主菜"),
    /**
     * 韩国料理
     */
    KOREA("韩国料理"),
    /**
     * 日本料理
     */
    JAPAN("日本料理"),
    /**
     * 饮料
     */
    DRINK("饮料"),
    /**
     * 甜品
     */
    SWEETS("甜品");

    private String name;

    DishEnum(String name) {
        this.name = name;
    }

    DishEnum() {
    }

    /**
     * 将String转换为枚举
     *
     * @param name 类名
     * @return 类名对应的枚举，如果类名错误则默认为主菜
     */
    public static DishEnum pauseToDishEnum(String name) {
        DishEnum dishEnum;
        switch (name) {
            case "主菜":
            default:
                dishEnum = ENTREE;
                break;
            case "韩国料理":
                dishEnum = KOREA;
                break;
            case "日本料理":
                dishEnum = JAPAN;
                break;
            case "饮料":
                dishEnum = DRINK;
                break;
            case "甜品":
                dishEnum = SWEETS;
                break;
        }
        return dishEnum;
    }

    public String getName() {
        return name;
    }
}
