package pl.infoshare.workandfun.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileActions<E> {

    private ObjectMapper mapper;

    @Autowired
    public FileActions(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<E> readObjectListFromBase(String path, Class<E> elementClass) {
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        try {
            return mapper.readValue(new FileReader(path), listType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
