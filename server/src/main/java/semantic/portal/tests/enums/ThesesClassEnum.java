package semantic.portal.tests.enums;

import lombok.Getter;

@Getter
public enum ThesesClassEnum {
    NONE("none", "Не задано"),
    CODE("code", "Code"),
    HTML_OUTPUT("html-output", "HTML результат"),
    OUTPUT("output", "Output"),
    URL("url", "URL довідки для поняття"),
    ABBREV("abbrev", "Абревіатура"),
    DEFINITION("definition", "Визначення"),
    DEMO_CODE("democode","Демонстраційний код"),
    LIST_ITEM("list-item", "Елемент списку"),
    C2V_DENY_CONCEPT("c2v-deny-concept", "Заборонити поняття"),
    C2V_DENY_FORM("c2v-deny-form", "Заборонити словоформу"),
    REVERSE_PECULIARITY("reverse-peculiarity", "Загальна в-сть вкладеного поняття"),
    PECULIARITY("peculiarity", "Загальна відомість"),
    IMAGE("image", "Зображення (шлях)"),
    C2V_SYNONYM("c2v-synonym", "Локальний синонім"),
    C2V_CREATED_HERE("c2v-created-here", "Місце створення поняття"),
    TO_LATIN("to-latin", "Переклад латинською"),
    TO_ENG("to-eng", "Переклад на англ."),
    TO_RU("to-ru", "Переклад на рос."),
    TO_UKR("to-ukr", "Переклад на укр."),
    DENOTATION("denotation", "Позначення"),
    PREDESTINATION("predestination", "Призначення"),
    EXAMPLE("example", "Приклад"),
    ATTACHING("attaching", "Прикріплення (авто)"),
    ATTACHING_STRONG("attaching-strong", "Прикріплення (жорстке)"),
    NOTE("note", "Примітка"),
    POSITION("position", "Розташування"),
    ABBR_DECODE("abbr-decode", "Розшифровка абрев."),
    SYNONYM("synonym", "Синонім"),
    SYNTAX("syntax", "Синтаксис"),
    LIST("list", "Список"),
    LIST_SEQUENCE("list-sequence", "Список - послідовність"),
    STRUCTURE("structure", "Структура"),
    ESSENCE("essence", "Сутність"),
    REVERSE_ESSENCE("reverse-essence", "Сутність вкладеного поняття"),
    ESSENCE_NOT_UNIC("essence-not-unic", "Сутність неунікальна"),
    ISA("isa", "Тип поняття (IS-A)");

    private final String value;
    private final String desc;

    ThesesClassEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
