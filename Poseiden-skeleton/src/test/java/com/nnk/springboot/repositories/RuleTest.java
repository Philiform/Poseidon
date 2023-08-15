package com.nnk.springboot.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Rule;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleTest.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RuleTest {

	/** The repository. */
	@Autowired
	private RuleRepository repository;

	/** The rule 1. */
	private Rule rule1;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rule1 = new Rule();
		rule1.setId(0);
		rule1.setName("Name 1");
		rule1.setDescription("Description 1");
		rule1.setJson("Json 1");
		rule1.setTemplate("Template 1");
		rule1.setSqlStr("SqlStr 1");
		rule1.setSqlPart("SqlPart 1");
	}

	/**
	 * Test when find all then return rule list.
	 */
	@Test
	void test_WhenFindAll_ThenReturnRuleList() {
		List<Rule> response = repository.findAll();

		assertThat(response.size()).isNotNull();
	}

	/**
	 * Test given good new rule when save rule then return saved rule.
	 */
	@Test
	void test_GivenGoodNewRule_WhenSaveRule_ThenReturnSavedRule() {
		Rule response = repository.save(rule1);

		//ruleList size = 2
		rule1.setId(3);

		assertThat(response).isEqualTo(rule1);
	}

	/**
	 * Test given modified rule when update rule then return updated rule.
	 */
	@Test
	void test_GivenModifiedRule_WhenUpdateRule_ThenReturnUpdatedRule() {
		Rule newRule = repository.save(rule1);

		newRule.setName("Name modified");

		Rule response = repository.save(newRule);

		assertThat(response.getName()).isEqualTo("Name modified");
	}

	/**
	 * Test given rule id when delete rule then delete rule.
	 */
	@Test
	void test_GivenRuleId_WhenDeleteRule_ThenDeleteRule() {
		Integer id = rule1.getId();
		repository.delete(rule1);
		Optional<Rule> response = repository.findById(id);

		assertThat(response).isEmpty();
	}

}
