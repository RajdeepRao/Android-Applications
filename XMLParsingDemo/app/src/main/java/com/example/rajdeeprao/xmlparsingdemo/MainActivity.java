package com.example.rajdeeprao.xmlparsingdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements GetData.IData {
    InputStream in;
    static ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData(this).execute("http://liisp.uncc.edu/~mshehab/api/xml/persons.xml");
        pd=new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();

    }

    @Override
    public void returnedValues(InputStream val) throws IOException, XmlPullParserException, SAXException {
        in=val;
        ArrayList<Person> personArrayList=new ArrayList<Person>();
        //personArrayList=parsePerson(val);
        PersonSaxParser.PersonSAXParser.parsePerson(val);
        Log.d("Size",Integer.toString(personArrayList.size()));
        for(int i=0;i<personArrayList.size();i++){
            Log.d("People",personArrayList.get(i).toString());
        }

    }
    static ArrayList<Person> parsePerson(InputStream s) throws XmlPullParserException, IOException {

        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(s,"UTF-8");
        Person person=null;
        ArrayList<Person> personList=new ArrayList<Person>();
        int event=parser.getEventType();

        while(event!=XmlPullParser.END_DOCUMENT){
            switch(event){
                case XmlPullParser.START_TAG:
                    if(parser.getName().equals("person")){
                        person=new Person();
                        person.setId(parser.getAttributeValue(null,"id"));
                    }
                    else if(parser.getName().equals("name")){
                        person.setName(parser.nextText().trim());
                    }
                    else if(parser.getName().equals("age")){
                        person.setAge(parser.nextText().trim());
                    }
                    else if(parser.getName().equals("department")){
                        person.setDepartment(parser.nextText().trim());
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("person")){
                        personList.add(person);
                        person=null;
                    }

                    break;

                default:
                    break;
            }
            event=parser.next();
        }

        return personList;
    }



}
