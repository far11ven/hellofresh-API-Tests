package com.hellofresh.api.tests;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import static org.hamcrest.Matchers.*;

public class JsonSchemaValidations{

	/*@Test 
	public void matches_string_schema_correctly() throws IOException {
		// Given
		String schema = IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream("products-schema.json"));

		// When
		get("/products").then().assertThat().body(matchesJsonSchema(schema));
	}

	
	@Test 
	public void matches_input_stream_schema_correctly() throws IOException {
		// Given
		InputStream schema = Thread.currentThread().getContextClassLoader().getResourceAsStream("products-schema.json");

		// When
		get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
	}

	@Test 
	public void matches_classpath_schema_correctly() throws IOException {
		// When
		get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json"));
	}

	@Test 
	public void works_with_expectation_api() throws IOException {
		// When
		expect().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json")).when().get("/products");
	}

	@Test public void
	can_mix_json_schema_validation_and_body_validations() throws IOException {
		// When
		get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json")).and().body("[0].name", equalTo("An ice sculpture"));
	}

	@Test 
	public void throws_assertion_error_when_schema_doesnt_match_json_content() throws IOException {
		exception.expect(AssertionError.class);
		exception.expectMessage("The content to match the given JSON schema.\n" +
				"error: instance type (object) does not match any allowed primitive type (allowed: [\"array\"])\n" +
				"    level: \"error\"\n" +
				"    schema: {\"loadingURI\":\"#\",\"pointer\":\"\"}\n" +
				"    instance: {\"pointer\":\"\"}\n" +
				"    domain: \"validation\"\n" +
				"    keyword: \"type\"\n" +
				"    found: \"object\"\n" +
				"    expected: [\"array\"]\n" +
				"\n" +
				"  Actual: { \"store\": {\n" +
				"    \"book\": [ \n" +
				"      { \"category\": \"reference\",\n" +
				"        \"author\": \"Nigel Rees\",\n" +
				"        \"title\": \"Sayings of the Century\",\n" +
				"        \"price\": 8.95\n" +
				"      },\n" +
				"      { \"category\": \"fiction\",\n" +
				"        \"author\": \"Evelyn Waugh\",\n" +
				"        \"title\": \"Sword of Honour\",\n" +
				"        \"price\": 12.99\n" +
				"      },\n" +
				"      { \"category\": \"fiction\",\n" +
				"        \"author\": \"Herman Melville\",\n" +
				"        \"title\": \"Moby Dick\",\n" +
				"        \"isbn\": \"0-553-21311-3\",\n" +
				"        \"price\": 8.99\n" +
				"      },\n" +
				"      { \"category\": \"fiction\",\n" +
				"        \"author\": \"J. R. R. Tolkien\",\n" +
				"        \"title\": \"The Lord of the Rings\",\n" +
				"        \"isbn\": \"0-395-19395-8\",\n" +
				"        \"price\": 22.99\n" +
				"      }\n" +
				"    ],\n" +
				"    \"bicycle\": {\n" +
				"      \"color\": \"red\",\n" +
				"      \"price\": 19.95    }\n" +
				"  }\n" +
				"}");

		// When
		get("/jsonStore").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json").using(settings().parseUriAndUrlsAsJsonNode(true)));
	}

	@Test public void
	json_schema_validator_supports_disabling_checked_validation_statically() {
		// Given
		JsonSchemaValidator.settings = settings().with().checkedValidation(false);

		// When
		try {
			get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json"));
		} finally {
			JsonSchemaValidator.reset();
		}
	}

	@Test public void
	json_schema_validator_supports_specifying_json_schema_factory_instance_statically() {
		// Given
		JsonSchemaValidator.settings = settings().with().jsonSchemaFactory(
				JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV3).freeze()).freeze());

		// When
		try {
			get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json"));
		} finally {
			JsonSchemaValidator.reset();
		}
	}

	@Test public void
	json_schema_validator_supports_specifying_json_schema_factory_instance_and_disabling_checked_validation_statically() {
		// Given
		JsonSchemaValidator.settings = settings().with().jsonSchemaFactory(
				JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV3).freeze()).freeze()).
				and().with().checkedValidation(false);

		// When
		try {
			get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json"));
		} finally {
			JsonSchemaValidator.reset();
		}
	}

	@Test public void
	json_schema_validator_supports_using_a_supplied_json_schema_factory_instance() {
		// Given
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();

		// When
		get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json").using(jsonSchemaFactory));
	}

	@Test public void
	json_schema_validator_supports_using_the_supplied_json_schema_validator_settings() {
		// When
		get("/products").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("products-schema.json").using(settings().with().checkedValidation(false)));
	}

	@Test public void
	json_schema_validator_supports_draft_03() {
		// when
		get("/jsonStore").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("store-schema.json"));
	}

	@Test public void
	greet_json_resource_conforms_to_the_greeting_schema() {
		given().
		param("firstName", "John").
		param("lastName", "Doe").
		when().
		get("/greetJSON").
		then().
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("greeting-schema.json"));
	}*/
}