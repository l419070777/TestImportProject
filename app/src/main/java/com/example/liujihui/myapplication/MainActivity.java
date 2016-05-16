package com.example.liujihui.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

        ArrayList<CameraList> cameraList = new ArrayList<>();
        for (int i = 0; i <2 ; i++) {
            CameraList camera = new CameraList();
            camera.cameraId = i + "cameraId";
            camera.cameraName = i + "cameraName";
            camera.school_status = 1;
            List iList = new ArrayList();
            for (int j = 0; j < 10; j++) {
                iList.add(j);
            }
            camera.classIds = iList;
            cameraList.add(camera);
        }
        String json = changeArrayDateToJson(cameraList);
        tv.setText(changeArrayDateToJson(cameraList));
        Log.d("asdad", json);

    }


    /**
     * 将数组转换为JSON格式的数据。
     * @param stoneList 数据源
     * @return JSON格式的数据
     */
    public static String changeArrayDateToJson(ArrayList<CameraList> stoneList){
        try {
            JSONObject object = new JSONObject();

            object.put("error_code", 0);
            object.put("service_time", 123123123);
            JSONObject objectData = new JSONObject();
            objectData.put("school_status", 1);
            objectData.put("need_pay", 1);
            int length = stoneList.size();
            JSONArray arrayCameraList = new JSONArray();

            for (int i = 0; i < length; i++) {
                JSONObject objectCameraList = new JSONObject();

                CameraList stone = stoneList.get(i);
                String cameraId = stone.cameraId;
                String cameraStatus = stone.cameraName;
                int schoolStatus = stone.school_status;
                objectCameraList.put("cameraId", cameraId);
                objectCameraList.put("cameraStatus", cameraStatus);
                objectCameraList.put("schoolStatus", schoolStatus);
                JSONArray iList = new JSONArray();
                for (int j = 0; i < 10 ; j++) {
                    JSONObject iData = new JSONObject();
                    iData.put("classId", i);
                    iList.put(iData);
                }
                objectCameraList.put("classIds", iList);

                arrayCameraList.put(objectCameraList);
            }
            objectData.put("cameraList", arrayCameraList);
            object.put("data", objectData );
            return object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
