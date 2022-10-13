package com.juliett.core.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.sql.Types.NULL;

public class SetPreparedStatement {

    /**
     * @param ps
     * @param keys must always start index of 1 in order for this to work
     */
    public static void set(PreparedStatement ps,
                           Map<Integer, Object> keys) throws SQLException {
        for (int psIndex : keys.keySet()) {
            Object item = keys.get(psIndex);
            substitute(ps, psIndex, item);
        }
    }

    public static void set(PreparedStatement ps,
                           List<Object> list) {
        int size = list.size();

        IntStream.range(0, size)//
                .forEach(index -> {
                    int psIndex = index + 1;

                    try {
                        substitute(ps, psIndex, list.get(index));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw ThrowableUtils.errorInstance(e);
                    }
                });
    }

    private static void substitute(PreparedStatement ps,
                                   Integer psIndex,
                                   Object obj) throws SQLException {

        // String
        if (obj instanceof String) {
            ps.setString(psIndex, (String) obj);

            // Long
        } else if (obj instanceof Long) {
            ps.setLong(psIndex, (Long) obj);

            // Integer
        } else if (obj instanceof Integer) {
            ps.setInt(psIndex, (Integer) obj);

            // BigDecimal
        } else if (obj instanceof BigDecimal) {
            ps.setBigDecimal(psIndex, (BigDecimal) obj);

            // Boolean
        } else if (obj instanceof Boolean) {
            ps.setBoolean(psIndex, (Boolean) obj);

            // Date and Time
        } else if (obj instanceof LocalDate || //
                obj instanceof LocalTime || //
                obj instanceof LocalDateTime || //
                obj instanceof OffsetDateTime) {

            ps.setObject(psIndex, obj);

            // NULL
        } else {
            ps.setNull(psIndex, NULL);
        }
    }

}
