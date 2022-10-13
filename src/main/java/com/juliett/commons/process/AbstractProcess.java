package com.juliett.commons.process;

import static com.juliett.core.model.HelperUtils.joinStringFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.servlets.DefaultServlet.SortManager.Order;

import com.juliett.api.model.XBlastResponse;
import com.juliett.api.model.enums.ResponseCode;
import com.juliett.core.model.ThrowableUtils;
import com.juliett.core.model.enums.Order;
import com.xurpas.development.core.logger.Logger;
import com.xurpas.development.core.tools.Util;
//import com.xurpas.xblast.core.commons.model.ThrowableUtils;

/**
 * Servlet implementation class AbstractProcess
 */
public class AbstractProcess {

    private static final long serialVersionUID = 1L;

    protected Logger logger;

    public AbstractProcess(Logger logger) {
        super();
        this.logger = logger;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void sendResponse(HttpServletResponse response,ResponseCode responseCode, Object body) throws IOException {
        response.setStatus(responseCode.getValue());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        if (body == null) {
            out.append(Util.serialize(new XBlastResponse(responseCode)));
            logger.writeLog(formatResponseLog(responseCode));
        } else if ("String".equals(body.getClass().getSimpleName())) {
            out.append(Util.serialize(new XBlastResponse(responseCode, body.toString())));
            logger.writeLog(formatResponseLog(responseCode));
        } else {
            int writeLogLengthLimit = 150;

            String serializedBody = Util.serialize(body);
            if (serializedBody.length() > writeLogLengthLimit) {
                String logBody = serializedBody.substring(0, writeLogLengthLimit) + "...";
                String responseLog = formatResponseLog(responseCode, logBody);
                logger.writeLog(responseLog);
                logger.debug(joinStringFormat("responseCode " + responseCode, getCallerStack(), responseLog));
            } else {
                String responseLog = formatResponseLog(responseCode, serializedBody);
                logger.writeLog(responseLog);
                logger.debug(joinStringFormat("responseCode " + responseCode, getCallerStack(), responseLog));

            }
            out.append(serializedBody);
        }
        out.flush();
        out.close();

    }

    public void sendError(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseCode.getValue());
        XBlastResponse responseBody = new XBlastResponse(responseCode);

        String serialize = Util.serialize(responseBody);
        logger.writeLog(formatResponseLog(responseCode, serialize));

//        logger.debug(joinStringFormat("responseCode " + responseCode, getCallerStack(), responseBody.toString()));
        response.getWriter().append(Util.serialize(responseBody));
    }

    public void sendError(HttpServletResponse response, ResponseCode responseCode, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(responseCode.getValue());
        XBlastResponse responseBody = null;

        responseBody = new XBlastResponse(responseCode, message);

        String serialize = Util.serialize(responseBody);
        logger.writeLog(formatResponseLog(responseCode, serialize));

        logger.debug(joinStringFormat("responseCode " + responseCode, getCallerStack(), responseBody.toString()));
        response.getWriter().append(Util.serialize(responseBody));
    }


    private String formatResponseLog(ResponseCode responseCode, String body) {

        StringBuilder format = new StringBuilder("Status = ").append(responseCode.getValue()).append(",");

        if (body == null) {
            format.append(" body = null ");
        } else {
            format.append(" body = ").append(body);
        }
        return format.toString();
    }

    private String formatResponseLog(ResponseCode responseCode) {
        return formatResponseLog(responseCode, null);
    }

    private static String getCallerStack(final int index) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[index].toString();
    }

    private static String getCallerStack() {
        // will throw Exception index out of bounds when called from main method
        return getCallerStack(4);
    }

    public static void m2() {

        System.out.println(getCallerStack());
    }

    /* New */

    public <T, U> void error(T input, U error) {
        logger.error(input + " : " + error);
    }

    public String getToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        logger.debug("authorizationHeader : " + authorizationHeader);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            error(authorizationHeader, "Invalid Authorization Header");
            return null;
        }

        logger.debug("token: " + authorizationHeader);

        String bearerToken = authorizationHeader.substring(7);

        return bearerToken;
    }

    public String getToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            error(authorizationHeader, "Invalid Authorization Header");
            return null;
        }

        logger.debug("token: " + authorizationHeader);

        String bearerToken = authorizationHeader.substring(7);

        return bearerToken;
    }

    public String getPathInfo(HttpServletRequest request) {
        return request.getPathInfo() == null ? null : request.getPathInfo().substring(1);
    }

    public <T> T deserialize(HttpServletRequest request, Class<T> tClass) {
        try {
            T deserialized = Util.deserialize(request, tClass);
            logger.debug(deserialized);

            return deserialized;
        } catch (IOException e) {
            logger.error(ThrowableUtils.stringify(e));
            return null;
        }
    }

    /**
     * @param request
     * @return (ASC | DESC) or null if order is null
     */
    public Order getOrderParam(HttpServletRequest request) {
        String order = request.getParameter("order");
        if (order == null) return null;

        return Order.find(order);
    }

    public Integer getIntParam(HttpServletRequest request, String paramName) throws NumberFormatException {
        String param = request.getParameter(paramName);
        logger.debug("getIntParam: " + paramName + " - " + param);
        if (param == null) return null;

        // if param is null || invalid number, will throw NumberFormatException
        return Integer.parseInt(param);
    }

    public Long getSubPathId(HttpServletRequest request) throws NumberFormatException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) return null;
        boolean isPathValidNumber = pathInfo.matches("/\\d+$");
        if (!isPathValidNumber) throw new NumberFormatException("Not valid Long Number");

        return Long.parseLong(pathInfo.replace("/", ""));
    }

    /**
     * @param request
     * @return [""] if pathInfo is "/"
     * @throws NumberFormatException on pathInfo == ( "/" | (/{words})+ | null )
     */
    public static List<Long> getSubPathIds(HttpServletRequest request) throws NumberFormatException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) throw new NumberFormatException("pathInfo is null");
        if (pathInfo.length() < 2) throw new IndexOutOfBoundsException("no subpath provided");

        String[] subPathIds = pathInfo                      //
                .substring(1)                   //
                .split("/");
        return Arrays.stream(subPathIds)                    //
                .map(Long::parseLong)                       //
                .collect(Collectors.toList());
    }

    public static List<String> getSubPaths(HttpServletRequest request) throws IllegalStateException, IndexOutOfBoundsException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) throw new IllegalStateException("pathInfo is null");
        if (pathInfo.length() < 2) throw new IndexOutOfBoundsException("no subpath provided");
        String[] subPaths = pathInfo                        //
                .substring(1)                   //
                .split("/");
        return Arrays.stream(subPaths)                      //
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
    }
}
