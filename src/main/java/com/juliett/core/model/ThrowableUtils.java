package com.juliett.core.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.exception.ExceptionUtils;


public class ThrowableUtils {

    public static String stringify(final Throwable throwable) {
        return stringify(throwable, 7);
    }

    public static String stringify(final Throwable throwable,
            final int lineLimit) {
        // print's stack trace
        throwable.printStackTrace();

        final StackTraceElement[] paramSTE = throwable.getStackTrace();
        String stringifyStackTrace = null;

        try {
            StackTraceElement[] limitedSTE = Stream.of(paramSTE)
                    .limit(lineLimit)
                    .toArray(StackTraceElement[]::new);
            throwable.setStackTrace(limitedSTE);

            stringifyStackTrace = ExceptionUtils.getStackTrace(throwable)
                    .replaceAll("[\\n\\r]+$", "");
        } finally {
            throwable.setStackTrace(paramSTE);
        }

        return stringifyStackTrace;
    }

    public static String stringify(Throwable[] throwables) {
        return Stream.of(throwables)
                .map(ThrowableUtils::stringify)
                .collect(Collectors.joining("\n"));
    }

    public static Error errorInstance(Throwable throwable) {
        Error error = new Error(throwable.getMessage());
        error.setStackTrace(throwable.getStackTrace());
        return error;
    }

}
