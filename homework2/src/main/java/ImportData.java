import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.*;

public class ImportData {

    private Logger logger = LoggerFactory.getLogger(ImportData.class);


    @Test
    public void main() {
        System.out.println("hello world");
    }


    public final static String HOST = "172.20.10.2";

    public final static String CLUSTERNAME = "homework";

    public final static int PORT = 9300;

    public  TransportClient getConnection() throws UnknownHostException {
        Settings settings = Settings.builder()
//                .put("client.transport.ping_timeout", 1000)
//                .put("client.transport.sniff" , true)
                .put("cluster.name" , CLUSTERNAME)
//                .put("thread_pool.search.size" , 20)
                .build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST) , PORT));

        return client;

    }

    @Test
    public void add() {
        try{
            XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                    .field("name","ayo")
                    .field("age" , 22)
                    .field("job" , "coder")
                    .endObject();

            String index = "homework";
            String type = "person";
            String id = "2";

            TransportClient client = this.getConnection();
            IndexResponse response = client.prepareIndex(index , type , id).setSource(content).get();

            System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                    + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());

//            client.prepareIndex(index , type , id).setSource(content);
            client.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }

    }


    @Test
    public void addMap() throws Exception{
        Map<String, Object> map = new HashMap<String,Object>();

        map.put("userName", "Daniel");
        map.put("sendDate", new Date());
//        map.put("msg", "hello Daniel");



//        test 一个值是List<Map<>> ...
        List<Map> mapList = new ArrayList();
        Map<String ,String> map1 = new HashedMap();
        map1.put("para1" , "1");
        map1.put("para2" , "2");
        mapList.add(map1);
        Map<String ,String> map2 = new HashedMap();
        map1.put("para3" , "3");
        map1.put("para4" , "4");
        mapList.add(map2);
        map.put("msg", mapList);



        TransportClient client = this.getConnection();
        IndexResponse response = client.prepareIndex("momo", "msg","2").setSource(map).get();

        System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());

        client.close();
    }

//    public static void indexStats(String index) {
//        IndicesAdminClient indicesAdminClient = transportClient.admin()
//                .indices();
//        IndicesStatsResponse response = indicesAdminClient.prepareStats(index)
//                .all().get();
//        ShardStats[] shardStatsArray = response.getShards();
//        for (ShardStats shardStats : shardStatsArray) {
//            logger.info("shardStats {}", shardStats.toString());
//        }
//        Map<String, IndexStats> indexStatsMap = response.getIndices();
//        for (String key : indexStatsMap.keySet()) {
//            logger.info("indexStats {}", indexStatsMap.get(key));
//        }
//        CommonStats commonStats = response.getTotal();
//        logger.info("total commonStats {}", commonStats.toString());
//        commonStats = response.getPrimaries();
//        logger.info("primaries commonStats {}", commonStats.toString());
//    }


//    1. read /*/*.json 到java程序中   file操作
//    2. 解析json文件，并包装成map的形式   按照index type id  +  map的方式put进入es中
//    3. 最后测试一下是否有一百组数据
//    4. 进行查询 解析对应的结果  -- 并将结果解析展现在前端页面上
//    5. 将每个结果块增加data-id的做法 每次进入详情页面后再次 查询这个id的结果，解析到前端页面即可

    @Test
    public void readJsonFile() throws UnknownHostException {

        TransportClient client = this.getConnection();



        String basePath = "C:\\Users\\NaYo\\Desktop\\作业二：电子卷宗智能检索系统任务要求\\电子卷宗智能检索系统任务要求\\电子卷宗OCR";
        File file = new File(basePath);
        File[] files = file.listFiles();


        for (File f : files) {
            if (f.isDirectory()) {
                File cur = new File(basePath + File.separator + f.getName());
                for (File ff : cur.listFiles()) {
                    if (ff.getName().endsWith(".json")) {
                        System.out.println(ff.getName());
                    }
                }
            }
//            System.out.println(f.getName());
        }
//        System.out.println(files.length);
    }


    @Test
    public void readSingleJsonFile() throws IOException {

        TransportClient client = this.getConnection();


        String path = "C:\\Users\\NaYo\\Desktop\\作业二：电子卷宗智能检索系统任务要求\\电子卷宗智能检索系统任务要求\\电子卷宗OCR\\3B2046E7-C896-4857-AAA0-A4CBAAC464A5\\3B2046E7-C896-4857-AAA0-A4CBAAC464A5.json";
        File file = new File(path);
//        if (file.getName().endsWith(".json")) {
//        }
        if (file.exists()) {
            System.out.println("ok");

        }
        String content = FileUtils.readFileToString(file , "UTF-8");
//        System.out.println(content);
        JSONObject jsonObject = JSONObject.fromObject(content);
        Iterator iterator = jsonObject.keys();
        Map<String, Object> parentMap = new HashMap();


        while (iterator.hasNext()) {
            String key =  (String) iterator.next();
            System.out.println(key + " : " +jsonObject.get(key));



            if (key.equals("textResult")) {
                JSONArray array = jsonObject.getJSONArray(key);
                for (int i=0;i<array.size();i++) {
                    Map<String , Object> childMap = new HashedMap();

                    JSONObject object = (JSONObject) array.get(i);
                    Iterator innerIterator = object.keys();
                    while (innerIterator.hasNext()) {
                        String innerKey = (String) innerIterator.next();
                        System.out.print(" | " + innerKey + " : " + object.get(innerKey));
                        childMap.put(innerKey , object.get(innerKey));
                    }
                    System.out.println();
                    IndexResponse response = client.prepareIndex("detail", "child",String.valueOf(i+1)).setSource(childMap).get();
                    System.out.println("map索引名称:" + response.getIndex() + " map类型:" + response.getType()
                            + " map文档ID:" + response.getId() + "当前实例map状态:" + response.status());
                }

                System.out.println(array.size());

            } else {
                parentMap.put(key, jsonObject.get(key));
            }

        }


        IndexResponse response = client.prepareIndex("brief", "parent","1").setSource(parentMap).get();

        System.out.println("map索引名称:" + response.getIndex() + " map类型:" + response.getType()
                + " map文档ID:" + response.getId() + "当前实例map状态:" + response.status());

        client.close();


    }



//    其中下方的高亮部分是一个list，并不进行解析
    @Test
    public void readSingleJsonFileIntoEs() {
        try{

            String path = "C:\\Users\\NaYo\\Desktop\\作业二：电子卷宗智能检索系统任务要求\\电子卷宗智能检索系统任务要求\\电子卷宗OCR\\3B2046E7-C896-4857-AAA0-A4CBAAC464A5\\3B2046E7-C896-4857-AAA0-A4CBAAC464A5.json";
            File file = new File(path);
            String text = FileUtils.readFileToString(file , "UTF-8");
            JSONObject jsonObject = JSONObject.fromObject(text);
            Iterator iterator = jsonObject.keys();

            Map<String, Object> map = new HashMap<String,Object>();

            while (iterator.hasNext()) {
                String key =  (String) iterator.next();
                System.out.println(key + " : " +jsonObject.get(key));
                map.put(key , jsonObject.get(key));
            }


            String index = "homework";
            String type = "person";
            String id = "1";



            TransportClient client = this.getConnection();
            IndexResponse response = client.prepareIndex(index , type , id).setSource(map).get();

            System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                    + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response.status());

            client.close();
        } catch (IOException e ) {
            e.printStackTrace();
        }

    }




    @Test
    public void readTotalJsonFile() throws IOException {

        TransportClient client = this.getConnection();

        String basePath = "C:\\Users\\NaYo\\Desktop\\作业二：电子卷宗智能检索系统任务要求\\电子卷宗智能检索系统任务要求\\电子卷宗OCR";
        File file = new File(basePath);

        if (!file.exists()) {
            return;
        }

        File[] files = file.listFiles();


        int brief_cnt = 1;
        int detail_cnt = 1;

        for (File f : files) {
            if (f.isDirectory()) {
                File cur = new File(basePath + File.separator + f.getName());


                for (File ff : cur.listFiles()) {

                    if (ff.exists() && ff.getName().endsWith(".json")) {

                        String content = FileUtils.readFileToString(ff , "UTF-8");
                        JSONObject jsonObject = JSONObject.fromObject(content);
                        Iterator iterator = jsonObject.keys();
                        Map<String, Object> parentMap = new HashMap();


                        while (iterator.hasNext()) {
                            String key =  (String) iterator.next();
                            System.out.println(key + " : " +jsonObject.get(key));


                            if (key.equals("textResult")) {
                                JSONArray array = jsonObject.getJSONArray(key);
                                for (int i=0;i<array.size();i++) {
                                    Map<String , Object> childMap = new HashedMap();

                                    JSONObject object = (JSONObject) array.get(i);
                                    Iterator innerIterator = object.keys();
                                    while (innerIterator.hasNext()) {
                                        String innerKey = (String) innerIterator.next();
//                                        System.out.print(" | " + innerKey + " : " + object.get(innerKey));
                                        childMap.put(innerKey , object.get(innerKey));
                                    }
//                                    System.out.println();
                                    childMap.put("parent" , String.valueOf(brief_cnt));

                                    IndexResponse response = client.prepareIndex("detail", "child",String.valueOf(detail_cnt)).setSource(childMap).get();
//                                    System.out.println("map索引名称:" + response.getIndex() + " map类型:" + response.getType()
//                                            + " map文档ID:" + response.getId() + "当前实例map状态:" + response.status());
                                    detail_cnt++;
                                }

                                System.out.println(array.size());

                            } else {
                                parentMap.put(key, jsonObject.get(key));
                            }

                        }

                        IndexResponse response = client.prepareIndex("brief", "parent",String.valueOf(brief_cnt)).setSource(parentMap).get();
//                        System.out.println("map索引名称:" + response.getIndex() + " map类型:" + response.getType()
//                                + " map文档ID:" + response.getId() + "当前实例map状态:" + response.status());
                        brief_cnt++;
                    }




                }
            }
        }

        client.close();

        System.out.println(brief_cnt + " - " + detail_cnt);

    }



//    匹配ocrText中的字符串查询  模糊检索
    @Test
    public void queryString() throws UnknownHostException {

        TransportClient client = this.getConnection();


        QueryStringQueryBuilder queryBuilders = QueryBuilders.queryStringQuery("南京")
                .field("ocrText");

//        setFrom setSize可以进行设置分页和返回数量
        SearchResponse searchResponse = client.prepareSearch("brief").setTypes("parent").setQuery(queryBuilders).setSize(50).get();
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        SearchHit[] hits2 = hits.getHits();
        for (SearchHit sh:hits2) {
            Map<String, Object> source = sh.getSourceAsMap();

            System.out.println(sh.getScore() + " " +source);

        }

        System.out.println("结果个数 : " + totalHits);
        client.close();
    }


//    用于匹配detail索引中child的parent列的值 和 brief索引中id对映起来进行的match
    @Test
    public void queryMatch() throws UnknownHostException {

        TransportClient client = this.getConnection();


        QueryBuilder qb = QueryBuilders.matchQuery("parent","1");
        SearchResponse searchResponse = client.prepareSearch("detail").setTypes("child").setQuery(qb).setSize(50).get();
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        SearchHit[] hits2 = hits.getHits();
        for (SearchHit sh:hits2) {
            Map<String, Object> source = sh.getSourceAsMap();

            System.out.println(sh.getScore() + " " +source);

        }

        System.out.println("结果个数 : " + totalHits);
        client.close();

    }

}
