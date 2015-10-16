Confidentiality example 2
=========================

This is the second example for the problem of confidentiality.

Here we have a list of patients, each of which has a list of prescriptions, which are private information. In this program, patient and prescription are both simplified to Integers. When adding a new prescription to a given patient, we first check if the patient already has this prescription in the database. The execution time varies between a patient who has no previous prescriptions (the loop is not even executed) and a patient who has a large number of prescriptions. Consequently, the execution time reveals private information for a patient.