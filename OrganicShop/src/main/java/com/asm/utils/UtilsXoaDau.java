package com.asm.utils;

public class UtilsXoaDau {
    public static String XoaDauVaKhoangTrang(String input) {
        String noAccent = input.replaceAll("[áàảãạăắằẳẵặâấầẩẫậ]", "a")
                                .replaceAll("[éèẻẽẹêếềểễệ]", "e")
                                .replaceAll("[óòỏõọôốồổỗộơớờởỡợ]", "o")
                                .replaceAll("[íìỉĩị]", "i")
                                .replaceAll("[úùủũụưứừửữự]", "u")
                                .replaceAll("[ýỳỷỹỵ]", "y")
                                .replaceAll("[đ]", "d")
                                .replaceAll("[ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬ]", "A")
                                .replaceAll("[ÉÈẺẼẸÊẾỀỂỄỆ]", "E")
                                .replaceAll("[ÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ]", "O")
                                .replaceAll("[ÍÌỈĨỊ]", "I")
                                .replaceAll("[ÚÙỦŨỤƯỨỪỬỮỰ]", "U")
                                .replaceAll("[ÝỲỶỸỴ]", "Y")
                                .replaceAll("[Đ]", "D")
                                .replaceAll("\\s+", ""); // Loại bỏ khoảng trắng
        return noAccent;
    }
}