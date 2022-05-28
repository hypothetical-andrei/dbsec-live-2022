-- delete anomaly
-- we delete john smith and jane doe
DELETE FROM anomalousstudents WHERE student_id =  "s01a";
DELETE FROM anomalousstudents WHERE student_id =  "s02b";
-- question : does the faculty have a database class?
-- can this question be answered if the data about the class was in the anomalousstudents table?