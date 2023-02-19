# Restaurant Recommendation Engine

Implementation is using Java/Springboot and a modified version of chain of responsibility pattern.
In order to add a new rule either implement **GenericRule.java** or **BooleanRecommendationRule.java**
These two interface provide the functionality of adding new rules. Remember to override
the `int org.springframework.core.Ordered.getOrder();` method to add the rule ordering information with each rule.

## Build

Import the repo in Eclipse/Intellij.

## Run

Execute the following main method to run the recommendation function with any arguments to get the desired results.

    com.solution.Application.main()