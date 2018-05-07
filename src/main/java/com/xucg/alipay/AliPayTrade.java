package com.xucg.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.xucg.common.AliPayClient;
import com.xucg.common.ResultJson;
import com.xucg.enums.AliPayEnum;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 统一收单交易
 *
 * @author xuchenguang
 * @date 2018/4/11
 */
public class AliPayTrade {
    private static final Log log = LogFactory.getLog("ALiPay SDK");
    private static final String FAST_INSTANT_TRADE_PAY = "FAST_INSTANT_TRADE_PAY";
    private static final String QUICK_WAP_WAY = "QUICK_WAP_WAY";

    /**
     * 交易创建
     * out_trade_no  商户订单号 *
     * total_amount  订单总金额，单位为元，精确到小数点后两位 *
     * subject       订单标题  *
     * body          对交易或商品的描述
     * buyer_id      买家的支付宝唯一用户号 *
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.create/
     *
     * @param payModel
     * @param notifyUrl
     * @return
     */
    public static String create(AlipayTradePayModel payModel, String notifyUrl) {

        try {
            AlipayClient client = AliPayClient.getAliPayClient();

            AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();

            //回调地址
            request.setNotifyUrl(notifyUrl);

            //将实体放入Request请求
            request.setBizModel(payModel);

            //返回信息
            AlipayTradeCreateResponse response = client.execute(request);

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }


    /**
     * 线下交易预创建
     * 支付宝二维码收款
     * out_trade_no 商户订单号 *
     * total_amount 订单总金额，单位为元，精确到小数点后两位 *
     * subject 订单标题 *
     * body 对交易或商品的描述
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.precreate/
     *
     * @param payModel
     * @param notifyUrl 回调
     * @return
     */
    public static String preCreate(AlipayTradePrecreateModel payModel, String notifyUrl) {
        try {

            AlipayClient alipayClient = AliPayClient.getAliPayClient();

            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();

            //回调地址
            request.setNotifyUrl(notifyUrl);

            //将实体放入Request请求
            request.setBizModel(payModel);

            //返回信息
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }

    /**
     * 手机网站支付接口2.0
     * out_trade_no 商户订单号 *
     * total_amount 订单总金额，单位为元，精确到小数点后两位 *
     * subject 订单标题 *
     * quit_url 用户付款中途退出返回商户网站的地址 *
     * body 对交易或商品的描述
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.wap.pay/
     *
     * @param payModel
     * @param notifyUrl
     * @param returnUrl
     * @return
     */
    public static String wapPay(AlipayTradeWapPayModel payModel, String notifyUrl, String returnUrl) {

        try {

            AlipayClient client = AliPayClient.getAliPayClient();

            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

            //回调地址
            request.setNotifyUrl(notifyUrl);

            //支付后返回的页面
            request.setReturnUrl(returnUrl);

            //产品销售码
            payModel.setProductCode(QUICK_WAP_WAY);
            //将实体放入Request请求
            request.setBizModel(payModel);

            AlipayTradeWapPayResponse response = client.pageExecute(request);

            if (response.isSuccess()) {

                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultJson.getResultJsonError();
    }

    /**
     * 电脑网站支付
     * 支付宝API文档：https://docs.open.alipay.com/270/alipay.trade.page.pay/
     *
     * @param payModel
     * @param notifyUrl
     * @param returnUrl
     * @return
     */
    public static String pagePay(AlipayTradePagePayModel payModel, String notifyUrl, String returnUrl) {

        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();
            //创建API对应的request
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

            //在公共参数中设置回跳和通知地址
            request.setReturnUrl(returnUrl);
            request.setNotifyUrl(notifyUrl);

            //产品销售码
            payModel.setProductCode(FAST_INSTANT_TRADE_PAY);

            //设置请求参数
            request.setBizModel(payModel);

            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }

    /**
     * app支付
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.app.pay/
     *
     * @param payModel
     * @param notifyUrl
     * @param returnUrl
     * @return
     */
    public static String appPay(AlipayTradeAppPayModel payModel, String notifyUrl, String returnUrl) {
        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();
            //创建API对应的request
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

            //在公共参数中设置回跳和通知地址
            request.setReturnUrl(returnUrl);
            request.setNotifyUrl(notifyUrl);
            //设置请求参数
            request.setBizModel(payModel);
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);

            log.info(response.getBody());

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }


    /**
     * 统一收单线下交易查询
     * out_trade_no 商户订单号   ~*
     * trade_no     支付宝交易号 ~*
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.query/
     *
     * @return
     */
    public static String query(AlipayTradeQueryModel queryModel) {
        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

            request.setBizModel(queryModel);

            AlipayTradeQueryResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }

    /**
     * 统一收单交易退款
     * out_trade_no 商户订单号~*
     * trade_no 支付宝交易号  ~*
     * refund_amount 退款金额  *
     * out_request_no   如需部分退款，则此参数必传
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.refund/
     *
     * @return
     */
    public static String refund(AlipayTradeRefundModel refundModel) {

        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

            request.setBizModel(refundModel);

            AlipayTradeRefundResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }

    /**
     * 统一收单交易退款查询
     * trade_no支付宝交易号  ~*
     * out_trade_no商户订单号 ~*
     * out_request_no退款请求号 *
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query/
     *
     * @param refundQueryModel
     * @return
     */
    public static String refundQuery(AlipayTradeFastpayRefundQueryModel refundQueryModel) {
        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();

            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

            request.setBizModel(refundQueryModel);

            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }

    /**
     * 统一收单交易关闭
     * trade_no     支付宝交易号 ~*
     * out_trade_no 商户订单号   ~*
     * operator_id  卖家端自定义的的操作员 ID
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.close/
     *
     * @param closeModel
     * @return
     */
    public static String close(AlipayTradeCloseModel closeModel) {
        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();

            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();

            request.setBizModel(closeModel);

            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }


    /**
     * 统一收单交易撤销
     * out_trade_no  商户订单号   ~*
     * trade_no      支付宝交易号 ~*
     * 支付宝API文档：https://docs.open.alipay.com/api_1/alipay.trade.cancel/
     *
     * @param cancelModel
     * @return
     */
    public static String cancel(AlipayTradeCancelModel cancelModel) {
        try {
            AlipayClient alipayClient = AliPayClient.getAliPayClient();

            AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();

            request.setBizModel(cancelModel);

            AlipayTradeCancelResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                return ResultJson.getResultJsonSuccess(response.getBody());
            }
            return ResultJson.getResultJsonFail(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultJson.getResultJsonError();
    }


    /**
     * 支付回调
     *
     * @param request
     * @return
     */
    public static String notify(HttpServletRequest request) {
        HttpServletResponse response = null;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            boolean signVerified = AlipaySignature.rsaCheckV1(buildParams(request), AliPayEnum.PUBLIC_KEY.getValue(),
                    AliPayEnum.CHARTSET.getValue(), AliPayEnum.SING_TYPE.getValue());
            if (signVerified) {
                String appId = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
                //商户订单号
                String outTradeNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                //支付宝交易号
                String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
                //交易状态
                String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

                //已支付或支付成功
                if (AliPayEnum.TRADE_SUCCESS.getValue().equals(tradeStatus) || AliPayEnum.TRADE_FINISHED.getValue().equals(tradeStatus)) {
                    Map<String, String> map = new HashMap<String, String>(16);
                    map.put("appId", appId);
                    map.put("outTradeNo", outTradeNo);
                    map.put("tradeNo", tradeNo);
                    map.put("tradeStatus", tradeStatus);
                    return ResultJson.getResultJsonSuccess(map);
                }
                out.println("fail");
                return ResultJson.getResultJsonFail();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("fail");
        return ResultJson.getResultJsonError();
    }

    /**
     * 获取回调返回的数据
     *
     * @param request
     * @return
     */
    public static Map<String, String> buildParams(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = null;
        try {
            params = new HashMap<String, String>(16);
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }
}
