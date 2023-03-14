INSERT INTO m_roles (roleid, rolename)
VALUES
 (1, 'ADMIN'),(2, 'HEAD_OF_HOME')
ON CONFLICT DO NOTHING;

INSERT INTO m_achievements (achievement_id, achievement_name)
VALUES 
(1, 'Academics'),
(2, 'Awards'),
(3, 'Entrepreneurship'),
(4, 'Felicitations')
ON CONFLICT DO NOTHING;
