package com.up42;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import com.up42.mapper.PropertiesMapper;
import com.up42.model.Properties;
import com.up42.repository.FeatureRepository;
import com.up42.repository.ImageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.nio.charset.Charset.defaultCharset;

@Component
public class DataLoader implements CommandLineRunner {

    private final FeatureRepository featureRepository;

    private final ImageRepository imageRepository;

    public DataLoader(FeatureRepository featureRepository, ImageRepository imageRepository) {
        this.featureRepository = featureRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String json = StreamUtils.copyToString(
                new ClassPathResource("source-data.json").getInputStream(), defaultCharset());
        Configuration.setDefaults(getConfiguration());
        Configuration conf = Configuration.defaultConfiguration();
        TypeRef<List<Properties>> typeRef = new TypeRef<List<Properties>>() {
        };
        DocumentContext context = JsonPath
                .using(conf)
                .parse(json);
        imageRepository.saveContext(context);
        context
                .read("$..properties", typeRef)
                .stream()
                .map(PropertiesMapper.INSTANCE::convert)
                .forEach(featureRepository::save);
    }

    private Configuration.Defaults getConfiguration() {
        return new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        };
    }
}
