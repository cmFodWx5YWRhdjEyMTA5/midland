package com.tinnovat.app.midland.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tinnovat.app.midland.XmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class XmlActivity extends AppCompatActivity  {

    // All static variables
    static final String URL = "https://api.androidhive.info/pizza/?format=xml";
   // static final String URL = "http://172.24.163.27:8095/ADInterface/services/ModelADService?wsdl";
    // XML node keys
    static final String KEY_ITEM = "item"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.tinnovat.app.midland.Activity.R.layout.activity_xml);
        Thread thread = new Thread(new Runnable() {

    @Override
    public void run() {
        try  {
            //Your code goes here
             ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XmlParser parser = new XmlParser();
        String xml = parser.getXmlFromUrl(URL); // getting XML
        Document doc = parser.getDomElement(xml); // getting DOM element

        NodeList nl = doc.getElementsByTagName(KEY_ITEM);
        // looping through all item nodes <item>
        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, parser.getValue(e, KEY_ID));
            map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
            map.put(KEY_COST, "Rs." + parser.getValue(e, KEY_COST));
            map.put(KEY_DESC, parser.getValue(e, KEY_DESC));

            // adding HashList to ArrayList
            menuItems.add(map);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        });

                thread.start();




        /*// Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.list_item,
                new String[] { KEY_NAME, KEY_DESC, KEY_COST }, new int[] {
                R.id.name, R.id.desciption, R.id.cost });

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();
        // listening to single listitem click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();

                // Starting new intent
                *//*Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                in.putExtra(KEY_NAME, name);
                in.putExtra(KEY_COST, cost);
                in.putExtra(KEY_DESC, description);
                startActivity(in);*//*

            }
        });*/
    }
    }


