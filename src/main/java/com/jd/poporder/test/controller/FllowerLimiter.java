package com.jd.poporder.test.controller;

import com.alibaba.fastjson.JSON;
import com.jd.poporder.analysis.AnalysisUnit;
import com.jd.poporder.analysis.Common;
import com.jd.poporder.analysis.Data;
import com.jd.poporder.analysis.Type;
import com.jd.poporder.annotation.PopOrderFlowResource;
import com.jd.poporder.constants.ContextNameConstants;
import com.jd.poporder.node.ClusterNode;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.slots.PopClusterBuilderSlot;
import com.jd.poporder.utils.ContextUtil;
import com.jd.poporder.utils.StatisticAnalysisNode;
import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api")
public class FllowerLimiter {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>();
    private static ArrayBlockingQueue<String> statisticAnalysisQueue = new ArrayBlockingQueue<>(10000);
    private static ArrayBlockingQueue<String> timeQueue = new ArrayBlockingQueue<>(10000);

    private static StatisticAnalysisNode statisticAnalysisNode = new StatisticAnalysisNode();
    static {
        List<Type> list = new ArrayList<>();
        Type type = new Type();
        type.setKey("synEsService");
        type.setText("同步ES服务");
        list.add(type);
        statisticAnalysisNode.setType(list);
    }
    private AtomicInteger count = new AtomicInteger();
    @PopOrderFlowResource("getOrderData")
    @RequestMapping("/orderdata")
    public String getOrderData(){
        return "success";
    }



    @PopOrderFlowResource("getOrderData1")
    @RequestMapping("/orderdata1")
    public String getOrderData1(){
        return "success";
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/trend")
    public String getOrderData1Qps() throws InterruptedException {
        PopClusterBuilderSlot popClusterBuilderSlot = new PopClusterBuilderSlot();
        ClusterNode clusterNode = PopClusterBuilderSlot.getClusterNodeByResourceName("getOrderData1");
        double v = clusterNode.passQps();
        if (statisticAnalysisQueue.size() > 30) {
            statisticAnalysisQueue.poll();
        }
        statisticAnalysisQueue.offer(String.valueOf(v));
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        if (simpleDateFormat == null){
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            simpleDateFormatThreadLocal.set(simpleDateFormat);
        }
        String time = simpleDateFormat.format(new Date());
        if (timeQueue.size() > 30) {
            timeQueue.poll();
        }
        timeQueue.offer(time);

        StatisticAnalysisNode statisticAnalysisNode = new StatisticAnalysisNode();

        Data data = new Data();
        data.setName("主表同步ES");
        data.setData(statisticAnalysisQueue.toArray(new String[10]));
        List<Data> listData = new ArrayList<>();
        listData.add(data);
        AnalysisUnit analysisUnit = new AnalysisUnit();
        analysisUnit.setTitle("同步ES服务");
        analysisUnit.setData(listData);
        statisticAnalysisNode.setSynEsService(analysisUnit);

        Common common = new Common();
        common.setTime(timeQueue.toArray(new String[10]));
        statisticAnalysisNode.setCommon(common);

        List<Type> listType = new ArrayList<>();
        Type type = new Type();
        type.setKey("synEsService");
        type.setText("同步ES服务");
        listType.add(type);
        statisticAnalysisNode.setType(listType);
        String result = JSON.toJSONString(statisticAnalysisNode);
        return result;
    }

    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping("/trend1")
    public String getQps(){
        DefaultNode node = (DefaultNode) ContextUtil.getNodeByContextName(ContextNameConstants.DEFAULT_CONTEXT_NAME);
        Random random = new Random();
        int value = random.nextInt(1000);
        int value2 = random.nextInt(500);
        return "{\n" +
                "    \"map\": {\n" +
                "        \"title\": \"地区销量趋势\",\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"name\": \"上海\",\n" +
                "                \"data\": [\n" +
                "                    \"155.13\",\n" +
                "                    \"154.65\",\n" +
                "                    \"171.46\",\n" +
                "                    \"164.38\",\n" +
                "                    \"237.23\",\n" +
                "                    \"300.65\",\n" +
                "                    \"240.29\",\n" +
                "                    \"232.07\",\n" +
                "                    \"193.31\",\n" +
                "                    \"136.70\",\n" +
                "                    \"48.64\",\n" +
                "                    \"90.20\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"北京\",\n" +
                "                \"data\": [\n" +
                "                    \"86.25\",\n" +
                "                    \"33.80\",\n" +
                "                    \"145.58\",\n" +
                "                    "+value2+",\n" +
                "                    \"176.09\",\n" +
                "                    \"132.41\",\n" +
                "                    \"291.05\",\n" +
                "                    \"191.89\",\n" +
                "                    \"151.54\",\n" +
                "                    \"94.25\",\n" +
                "                    \"141.75\",\n" +
                "                    \"157.14\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"深圳\",\n" +
                "                \"data\": [\n" +
                "                    \"143.94\",\n" +
                "                    \"186.29\",\n" +
                "                    \"183.64\",\n" +
                "                    \"251.48\",\n" +
                "                    \"195.48\",\n" +
                "                    \"152.16\",\n" +
                "                    \"52.47\",\n" +
                "                    \"184.12\",\n" +
                "                    \"203.79\",\n" +
                "                    \"39.16\",\n" +
                "                    \"56.37\",\n" +
                "                    \"161.64\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"广州\",\n" +
                "                \"data\": [\n" +
                "                    \"57.60\",\n" +
                "                    \"77.61\",\n" +
                "                    \"307.24\",\n" +
                "                    \"165.05\",\n" +
                "                    \"175.41\",\n" +
                "                    \"276.88\",\n" +
                "                    \"269.04\",\n" +
                "                    \"296.11\",\n" +
                "                    \"105.31\",\n" +
                "                    \"283.39\",\n" +
                "                    \"134.08\",\n" +
                "                    \"265.38\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"重庆\",\n" +
                "                \"data\": [\n" +
                "                    \"200.82\",\n" +
                "                    "+value+",\n" +
                "                    \"249.80\",\n" +
                "                    \"222.67\",\n" +
                "                    \"216.98\",\n" +
                "                    \"60.12\",\n" +
                "                    \"309.68\",\n" +
                "                    \"273.35\",\n" +
                "                    \"150.99\",\n" +
                "                    \"251.97\",\n" +
                "                    \"26.15\",\n" +
                "                    \"186.99\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"seller\": {\n" +
                "        \"title\": \"商家销量趋势\",\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"name\": \"商家1\",\n" +
                "                \"data\": [\n" +
                "                    \"33.00\",\n" +
                "                    \"86.07\",\n" +
                "                    \"28.77\",\n" +
                "                    \"34.29\",\n" +
                "                    \"102.45\",\n" +
                "                    \"0.30\",\n" +
                "                    \"50.50\",\n" +
                "                    \"21.70\",\n" +
                "                    \"25.41\",\n" +
                "                    \"25.71\",\n" +
                "                    \"66.90\",\n" +
                "                    \"63.29\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"商家2\",\n" +
                "                \"data\": [\n" +
                "                    \"12.83\",\n" +
                "                    \"102.42\",\n" +
                "                    \"37.37\",\n" +
                "                    \"95.55\",\n" +
                "                    \"45.45\",\n" +
                "                    \"112.72\",\n" +
                "                    \"113.53\",\n" +
                "                    \"106.41\",\n" +
                "                    \"75.67\",\n" +
                "                    \"113.91\",\n" +
                "                    \"37.32\",\n" +
                "                    \"28.04\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"商家3\",\n" +
                "                \"data\": [\n" +
                "                    \"73.54\",\n" +
                "                    \"40.92\",\n" +
                "                    \"89.81\",\n" +
                "                    \"113.41\",\n" +
                "                    \"76.34\",\n" +
                "                    \"107.15\",\n" +
                "                    \"55.61\",\n" +
                "                    \"0.33\",\n" +
                "                    \"106.29\",\n" +
                "                    \"78.30\",\n" +
                "                    \"98.05\",\n" +
                "                    \"38.67\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"商家4\",\n" +
                "                \"data\": [\n" +
                "                    \"47.19\",\n" +
                "                    \"73.57\",\n" +
                "                    \"44.60\",\n" +
                "                    \"84.03\",\n" +
                "                    \"62.82\",\n" +
                "                    \"15.65\",\n" +
                "                    \"64.72\",\n" +
                "                    \"88.98\",\n" +
                "                    \"29.25\",\n" +
                "                    \"5.41\",\n" +
                "                    \"79.11\",\n" +
                "                    \"118.46\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"商家5\",\n" +
                "                \"data\": [\n" +
                "                    \"74.84\",\n" +
                "                    \"116.45\",\n" +
                "                    \"107.69\",\n" +
                "                    \"11.03\",\n" +
                "                    \"17.31\",\n" +
                "                    \"42.22\",\n" +
                "                    \"97.60\",\n" +
                "                    \"108.64\",\n" +
                "                    \"43.87\",\n" +
                "                    \"110.65\",\n" +
                "                    \"5.96\",\n" +
                "                    \"38.41\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"commodity\": {\n" +
                "        \"title\": \"商品销量趋势\",\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"name\": \"女装\",\n" +
                "                \"data\": [\n" +
                "                    \"47.71\",\n" +
                "                    \"13.34\",\n" +
                "                    \"19.30\",\n" +
                "                    \"7.93\",\n" +
                "                    \"41.93\",\n" +
                "                    \"23.01\",\n" +
                "                    \"22.63\",\n" +
                "                    \"26.91\",\n" +
                "                    \"0.62\",\n" +
                "                    \"39.23\",\n" +
                "                    \"48.74\",\n" +
                "                    \"29.48\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"手机数码\",\n" +
                "                \"data\": [\n" +
                "                    \"46.66\",\n" +
                "                    \"46.52\",\n" +
                "                    \"23.65\",\n" +
                "                    \"1.73\",\n" +
                "                    \"44.26\",\n" +
                "                    \"47.07\",\n" +
                "                    \"17.86\",\n" +
                "                    \"40.20\",\n" +
                "                    \"3.78\",\n" +
                "                    \"31.46\",\n" +
                "                    \"28.01\",\n" +
                "                    \"8.63\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"男装\",\n" +
                "                \"data\": [\n" +
                "                    \"26.98\",\n" +
                "                    \"30.71\",\n" +
                "                    \"42.59\",\n" +
                "                    \"29.50\",\n" +
                "                    \"26.86\",\n" +
                "                    \"17.65\",\n" +
                "                    \"30.15\",\n" +
                "                    \"15.85\",\n" +
                "                    \"9.28\",\n" +
                "                    \"30.20\",\n" +
                "                    \"32.35\",\n" +
                "                    \"34.46\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"大家电\",\n" +
                "                \"data\": [\n" +
                "                    \"20.26\",\n" +
                "                    \"46.23\",\n" +
                "                    \"43.84\",\n" +
                "                    \"46.75\",\n" +
                "                    \"28.29\",\n" +
                "                    \"32.36\",\n" +
                "                    \"45.30\",\n" +
                "                    \"16.73\",\n" +
                "                    \"40.40\",\n" +
                "                    \"45.07\",\n" +
                "                    \"29.86\",\n" +
                "                    \"41.92\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"美妆护肤\",\n" +
                "                \"data\": [\n" +
                "                    \"7.58\",\n" +
                "                    \"23.66\",\n" +
                "                    \"39.78\",\n" +
                "                    \"30.20\",\n" +
                "                    \"25.72\",\n" +
                "                    \"36.20\",\n" +
                "                    \"47.55\",\n" +
                "                    \"35.39\",\n" +
                "                    \"27.85\",\n" +
                "                    \"37.56\",\n" +
                "                    \"16.91\",\n" +
                "                    \"3.91\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"common\": {\n" +
                "        \"time\": [\n" +
                "            \"2021/4/4 17:46:00\",\n" +
                "            \"2021/4/4 17:46:10\",\n" +
                "            \"2021/4/4 17:46:20\",\n" +
                "            \"2021/4/4 17:46:30\",\n" +
                "            \"2021/4/4 17:46:40\",\n" +
                "            \"2021/4/4 17:46:50\",\n" +
                "            \"2021/4/4 17:47:00\",\n" +
                "            \"2021/4/4 17:47:10\",\n" +
                "            \"2021/4/4 17:47:20\",\n" +
                "            \"2021/4/4 17:47:30\",\n" +
                "            \"2021/4/4 17:47:40\",\n" +
                "            \"2021/4/4 17:47:50\"\n" +
                "        ]\n" +
                "    },\n" +
                "    \"type\": [\n" +
                "        {\n" +
                "            \"key\": \"map\",\n" +
                "            \"text\": \"地区销量趋势\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"seller\",\n" +
                "            \"text\": \"商家销量趋势\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"commodity\",\n" +
                "            \"text\": \"商品销量趋势\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
