package com.example.rajdeeprao.xmlparsingdemo;

import android.util.Log;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by rajdeeprao on 2/13/17.
 */

public class PersonSaxParser {
    static public class PersonSAXParser extends DefaultHandler{
        ArrayList<Person> personList;
        Person person;
        StringBuilder sb;

        static public ArrayList<Person> parsePerson(InputStream in) throws IOException, SAXException {
            ArrayList<Person> personList=new ArrayList<Person>();

            PersonSAXParser parser=new PersonSAXParser();
            Xml.parse(in,Xml.Encoding.UTF_8,parser);
            return parser.getPersons();

        }

        @Override
        public void startDocument() throws SAXException {
            personList=new ArrayList<Person>();
            sb=new StringBuilder();
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("person")){
                person=new Person();
                person.setId(attributes.getValue("id").trim());
            }


        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("person")){
                Log.d("Demo",person.toString());
                personList.add(person);
            }else if(localName.equals("name")){
                person.setName(sb.toString().trim());
            }
            else if(localName.equals("age")){
                person.setAge(sb.toString().trim());
            }
            else if(localName.equals("department")) {
                person.setDepartment(sb.toString().trim());
            }
            sb.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            sb.append(ch, start, length);
        }

        public ArrayList<Person> getPersons() {
            return personList;
        }
    }
}
