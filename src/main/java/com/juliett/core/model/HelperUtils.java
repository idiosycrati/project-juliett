package com.juliett.core.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelperUtils {

    /**
     * @param sb         target StringBuilder receiver
     * @param columnName column name to append to StringBuilder
     * @param values     container of present values
     */
    public static Consumer<Object> listConsumer(final StringBuilder sb,
                                                final String columnName,
                                                final List<Object> values) {
        return object -> {
            sb.append(String.format(" AND %s = ? ", columnName));
            values.add(object);
        };
    }

    public static <T> Consumer<T> listConsumer(final String columnName,
                                               final Consumer<String> andQueryConsumer,
                                               final Consumer<T> objectConsumer) {
        return object -> {
            andQueryConsumer.accept(String.format(" AND %s = ? ", columnName));
            objectConsumer.accept(object);
        };
    }

    public static <T> Consumer<T> handleConsumer(ConsumerException<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Throwable e) {
                e.printStackTrace();
                throw ThrowableUtils.errorInstance(e);
            }
        };
    }

    public static <T, K> BiConsumer<T, K> handleConsumer(BiConsumerException<T, K> consumer) {
        return (t, k) -> {
            try {
                consumer.accept(t, k);
            } catch (Throwable e) {
                e.printStackTrace();
                throw ThrowableUtils.errorInstance(e);
            }
        };
    }

    public static <T> Stream<T> collectionToStream(Collection<T> collection) {
        return Optional.ofNullable(collection).map(Collection::stream).orElseGet(Stream::empty);
    }

    @FunctionalInterface
    public interface ConsumerException<T> {

        void accept(T item) throws Throwable;

    }

    @FunctionalInterface
    public interface BiConsumerException<T, K> {

        void accept(T item1,
                    K item2) throws Throwable;

    }

    public static <T> List<T> collectionToList(Collection<T> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty)
                .collect(toList());
    }

    public static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String joinStringFormat(String... strings) {
        return String.join(" : ", strings);
    }

    public static String concatWithWhitespace(Object... strings) {
        return concatString(" ", strings);
    }

    public static String concatString(String delimiter, Object... objects) {
        return Arrays.stream(objects).map(String::valueOf).collect(Collectors.joining(delimiter));
    }

    public static String getTextFromPart(Part part) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[2048];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
        	
            value.append(buffer, 0, length);
        }
        return value.toString();
    }
    
    public static String[] getPartsFromRequest(HttpServletRequest request) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            value.append(line);
            value.append("\n");
        }
		reader.close();
		String content = value.toString().replaceAll("(-){6,}([0-9A-Za-z]){10,}|(--\\s)", "");
		String[] parts = content.split("\n\n\n");
        return parts;
    }
    
    public static String formatMobileNumber(String number) {
    	number = number.replaceAll("\\s+","");
    	if(number.length() == 11 && number.charAt(0) == '0' && number.charAt(1) == '9') {
    		return number;
    	}else if(number.length() == 10 && number.charAt(0) == '9') {
    		number = '0' + number;
    		return number;
    	}
    	return "";
    }
}
