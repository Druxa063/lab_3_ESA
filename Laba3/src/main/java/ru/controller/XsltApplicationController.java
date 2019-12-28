package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.ProcessingInstruction;
import ru.model.Employee;
import ru.model.EmployeeList;
import ru.service.EmployeeServiceImpl;
import ru.service.Helper;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;

@RestController
public class XsltApplicationController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    private String xmlToString(Document document, String type) throws TransformerException {
        ProcessingInstruction i = document.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"xsl/" + type + "\"");
        document.insertBefore(i, document.getDocumentElement());
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));
        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
        return output;
    }

    @RequestMapping(value = "/xslt/employees", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> watchEmployees() throws TransformerException {
        List<Employee> employees = employeeService.getAll();
        return watchEntities("employees", new EmployeeList(employees), EmployeeList.class);
    }

    private <T> ResponseEntity<String> watchEntities(String type, T object, Class<T> tClass) throws TransformerException {
        String element = Helper.jaxbObjectToXML(object, tClass);
        Document document = Helper.getDocument(element);
        return new ResponseEntity<>(xmlToString(document, type + ".xslt"), HttpStatus.OK);
    }
}
