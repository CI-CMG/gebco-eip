package gebcoeip;

import gebcoeip.json.AggregationMessage;
import gebcoeip.json.FileProcessingMessage;
import org.isotc211._2005.gco.CharacterStringPropertyType;
import org.isotc211._2005.gco.ObjectFactory;
import org.isotc211._2005.gmi.MIMetadata;
import org.isotc211._2005.gmi.MIMetadataType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class IsoMetadataProcessor {

    public static final ObjectFactory gco = new ObjectFactory();
    public static final org.isotc211._2005.gmi.ObjectFactory gmi = new org.isotc211._2005.gmi.ObjectFactory();

    public String generateIsoMetadata(FileProcessingMessage message) throws JAXBException {
        MIMetadataType miMetadataType = gmi.createMIMetadataType();
        miMetadataType.setFileIdentifier(newCharacterString(message.getFileId()));

        JAXBContext context = JAXBContext.newInstance("org.isotc211._2005.gmi");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.isotc211.org/2005/gmi https://data.noaa.gov/resources/iso19139/schema.xsd");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        marshaller.marshal(new MIMetadata(miMetadataType), out);
        return out.toString(StandardCharsets.UTF_8);

    }

    private static CharacterStringPropertyType newCharacterString(String value) {
        CharacterStringPropertyType cs = gco.createCharacterStringPropertyType();
        cs.setCharacterString(gco.createCharacterString(value));
        return cs;
    }

    public AggregationMessage aggregate(FileProcessingMessage message) {
        AggregationMessage aggregationMessage = new AggregationMessage();
        aggregationMessage.setFileId(message.getFileId());
        aggregationMessage.setMessage(message);
        return aggregationMessage;
    }
}
