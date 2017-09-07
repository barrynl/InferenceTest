Test case Openllet reasoner
===========================

The InferenceTest.java file contains a test in which you can see the behaviour of Openllet when updating a Apache Jena InfModel. After adding a statement, it works fine and shows the inferred statements as well, however, when removing the same statement again, weird stuff happens. The removed statement still shows up, but not all of its inferred statements are there...

It might be a bug in Openllet. The issue is here: https://github.com/Galigator/openllet/issues/21