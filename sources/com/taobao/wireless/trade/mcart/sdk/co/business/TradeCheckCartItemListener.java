package com.taobao.wireless.trade.mcart.sdk.co.business;

import com.alibaba.fastjson.JSONObject;
import com.taobao.wireless.trade.mcart.sdk.co.biz.ComponentBizUtil;
import com.taobao.wireless.trade.mcart.sdk.co.mtop.MtopTradeCheckCartItemResponse;
import com.taobao.wireless.trade.mcart.sdk.constant.CartFrom;
import com.taobao.wireless.trade.mcart.sdk.engine.CartEngineForMtop;
import mtopsdk.mtop.domain.BaseOutDo;
import mtopsdk.mtop.domain.MtopResponse;

public abstract class TradeCheckCartItemListener extends AbstractCartRemoteBaseListener {
    public abstract void onSuccessExt(int i, MtopResponse mtopResponse, BaseOutDo baseOutDo, Object obj);

    public TradeCheckCartItemListener(CartFrom cartFrom) {
        super(cartFrom);
    }

    public void onSuccess(int requestType, MtopResponse response, BaseOutDo pojo, Object context) {
        String data;
        super.onSuccess(requestType, response, pojo, context);
        if (!(pojo == null || !(pojo instanceof MtopTradeCheckCartItemResponse) || (data = ((MtopTradeCheckCartItemResponse) pojo).getData()) == null)) {
            CartEngineForMtop.getInstance(this.cartFrom).getParseModule().parse(JSONObject.parseObject(data));
        }
        refreshFooterComponentInfo();
        onSuccessExt(requestType, response, pojo, context);
    }

    public void refreshFooterComponentInfo() {
        ComponentBizUtil.refreshAllShopAndCheckAllComponentCheckStatus(this.cartFrom);
        ComponentBizUtil.refreshRealQuantityWeightAndSubmitComponentInfo(this.cartFrom);
    }
}
