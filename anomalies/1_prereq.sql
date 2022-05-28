-- the table is not normalized and thus lends itself to anomalies
CREATE TABLE  `ismv3`.`anomalousstudents` (
`student_id` VARCHAR( 255 ) NOT NULL ,
`full_name` VARCHAR( 255 ) NOT NULL ,
`address` VARCHAR( 255 ) NOT NULL ,
`course` VARCHAR( 255 ) NOT NULL ,
`year` VARCHAR( 255 ) NOT NULL
) ENGINE = MYISAM ;


-- initial correct data
INSERT INTO  `ismv3`.`anomalousstudents` (
`student_id` ,
`full_name` ,
`address` ,
`course` ,
`year`
)
VALUES (
's01a',  'john smith',  'bucharest, romania',  'databases',  '3'
), (
's02b',  'jane doe',  'bucharest, romania',  'databases',  '3'
), (
's03a',  'john doe',  'bucharest, romania',  'networks',  '3'
), (
's04a',  'jim jones',  'bucharest, romania',  'networks',  '3'
), (
's02b',  'jane doe',  'bucharest, romania',  'networks',  '3'
);
