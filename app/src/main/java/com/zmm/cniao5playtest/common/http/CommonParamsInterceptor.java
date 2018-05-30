package com.zmm.cniao5playtest.common.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zmm.cniao5playtest.common.Constant;
import com.zmm.cniao5playtest.common.util.ACache;
import com.zmm.cniao5playtest.common.util.DensityUtil;
import com.zmm.cniao5playtest.common.util.DeviceUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.common.http
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class CommonParamsInterceptor  implements Interceptor {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    private Gson mGson;
    private Context mContext;

    public CommonParamsInterceptor(Context context,Gson gson){

        this.mContext = context;
        this.mGson = gson;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request request = chain.request(); // 112.124.22.238:8081/course_api/cniaoplay/featured?p={'page':0}


        try {
                String method = request.method();

                HashMap<String,Object> commomParamsMap = new HashMap<>();

//                commomParamsMap.put(Constant.IMEI, DeviceUtils.getIMEI(mContext));
                commomParamsMap.put(Constant.IMEI, "test");
                commomParamsMap.put(Constant.MODEL,DeviceUtils.getModel());
                commomParamsMap.put(Constant.LANGUAGE,DeviceUtils.getLanguage());
                commomParamsMap.put(Constant.os,DeviceUtils.getBuildVersionIncremental());
                commomParamsMap.put(Constant.RESOLUTION, DensityUtil.getScreenW(mContext)+"*" + DensityUtil.getScreenH(mContext));
                commomParamsMap.put(Constant.SDK, DeviceUtils.getBuildVersionSDK()+"");
                commomParamsMap.put(Constant.DENSITY_SCALE_FACTOR,mContext.getResources().getDisplayMetrics().density+"");


                String token = ACache.get(mContext).getAsString(Constant.TOKEN);
                commomParamsMap.put(Constant.TOKEN,token==null?"":token);


                if(method.equals("GET")){


                    HttpUrl httpUrl =  request.url();

                    System.out.println("---Interceptor  httpUrl = "+httpUrl);

                    HashMap<String,Object> rootMap = new HashMap<>();

                    Set<String> paramNames =  httpUrl.queryParameterNames();

                    for (String key :paramNames){

                        if(Constant.PARAM.equals(key)){

                            String oldParamJson =  httpUrl.queryParameter(Constant.PARAM);
                            //有可能，是不携带任何参数访问
                            if(oldParamJson != null){
                                HashMap<String,Object> p =  mGson.fromJson(oldParamJson,HashMap.class); // 原始参数

                                if(p !=null){
                                    for (Map.Entry<String,Object> entry :p.entrySet()){

                                        rootMap.put(entry.getKey(),entry.getValue());
                                    }
                                }
                            }
                        } else {
                            rootMap.put(key,httpUrl.queryParameter(key));
                        }
                    }



                    rootMap.put("publicParams",commomParamsMap); // 重新组装
                    String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}


                    String url = httpUrl.toString();

                    System.out.println("---Interceptor  url1 = "+url);

                    int index = url.indexOf("?");
                    if(index>0){
                        url = url.substring(0,index);
                    }

                    url = url+"?"+Constant.PARAM+"="+newJsonParams; //  http://112.124.22.238:8081/course_api/cniaoplay/featured?p= {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}

                    System.out.println("---Interceptor  url2 = "+url);

                    request = request.newBuilder().url(url).build();

                } else  if(method.equals("POST")){


                    RequestBody body = request.body();


                    HashMap<String,Object> rootMap = new HashMap<>();
                    if(body instanceof FormBody){ // form 表单

                        for (int i=0;i<((FormBody) body).size();i++){

                            rootMap.put(((FormBody) body).encodedName(i),((FormBody) body).encodedValue(i));
                        }

                    } else{

                        Buffer buffer = new Buffer();

                        body.writeTo(buffer);

                        String oldJsonParams =  buffer.readUtf8();

                        rootMap = mGson.fromJson(oldJsonParams,HashMap.class); // 原始参数
                        rootMap.put("publicParams",commomParamsMap); // 重新组装
                        String newJsonParams = mGson.toJson(rootMap); // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}


                        request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();


                    }

                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }


        return chain.proceed(request);
    }
}
