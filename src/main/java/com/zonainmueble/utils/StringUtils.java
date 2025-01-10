package com.zonainmueble.utils;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

public class StringUtils {
  public static String removeAccents(String input) {
    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
    return normalized.replaceAll("\\p{M}", "");
  }

  public static String toUTF8(String input) {
    byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
    return new String(bytes, StandardCharsets.UTF_8);
  }

  public static String sanitizedUTF8(String input) {
    return toUTF8(input.replaceAll("[^a-zA-Z0-9-]", ""));
  }
}
