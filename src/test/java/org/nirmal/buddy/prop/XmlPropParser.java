package org.nirmal.buddy.prop;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.collect.Lists;

/**
 *
 * @author nirmal.s
 *
 */
@SuppressWarnings("all")
public class XmlPropParser {

    private static Pattern REGEX_PATTERN_PROP = Pattern.compile("\\$\\{([^}]+)\\}");
    private static Pattern REGEX_PATTERN_DW_PROP = Pattern.compile("p\\('(.+)'\\)");

    private List<String> fileToIgnore;
    private int parsedFilesCount = 0;
    private TreeSet<String> keys = new TreeSet<String>();

    private String path;


    public XmlPropParser(String path, List<String> ignore) {
        super();
        fileToIgnore = ignore;
        this.path = path;
    }

    public XmlPropParser(String path) {
        this(path,  Lists.newArrayList("pom.xml", "log4j2.xml"));
    }
    /**
     * @return
     * @throws Exception
     */
    public TreeSet<String> getAllProperties() throws Exception {
        keys.clear();
        parsedFilesCount = 0;

        Collection<File> files = FileUtils.listFiles(new File(path), new String[]{"xml"}, true);
        for (File file : files) {
            if(fileToIgnore.contains(file.getName()))
                continue;
            parseFile(file);
        }
        return new TreeSet<String>(keys);
    }
    private void parseFile(File file)  {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(file);
            Node children = doc.getChildNodes().item(0);
            processNodeAttributes(children);
        } catch (Exception e) {
            System.err.println("\n\nSkipped file "+file.getName()+", Error: "+e.getClass().getName()+": "+ e.getMessage()+"\n\n");
        }
        parsedFilesCount++;
    }

    private void processNodeAttributes(Node item) throws Exception {
        if (item.getNodeType() == Node.ELEMENT_NODE) {
            String name = item.getNodeName();
            for (int j = 0; j < item.getAttributes().getLength(); j++) {
                String content = item.getAttributes().item(j).getNodeValue();
                addKeysByPattern(content,REGEX_PATTERN_PROP);
            }
            NodeList nodeList  = item.getChildNodes();
            if(nodeList!=null){
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node child = nodeList.item(i);
                    processNodeAttributes(child);
                }
            }
        } else if (item.getNodeType() == Node.TEXT_NODE){
            String content = item.getTextContent();
            addKeysByPattern(content,REGEX_PATTERN_PROP);
        } else if (item.getNodeType() == Node.COMMENT_NODE ){
        } else if ( item.getNodeType() == Node.CDATA_SECTION_NODE){
            addKeysByPattern(item.getTextContent(),REGEX_PATTERN_PROP,REGEX_PATTERN_DW_PROP);
        }else {
            System.out.println("Node Type: "+item.getNodeType());
        }

    }

    private void addKeysByPattern(String val,Pattern...patterns) {
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(val);
            while (m.find()) {
                String data = m.group(1);
                if(!isBlank(data))
                    keys.add(data);
            }
        }

    }

    public static void main(String[] args) {
        try {
            XmlPropParser parse = new XmlPropParser("/Users/nirmal.s/Documents/Conns/svn/Branches/esb/mule/ecom/MTW-125/src/main/app");
            TreeSet<String> keys = parse.getAllProperties();
            System.out.println("--> "+keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getParsedFilesCount() {
        return parsedFilesCount;
    }

}
