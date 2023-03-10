INSERT INTO m_roles (roleid, rolename)
VALUES
 (1, 'ADMIN'),(2, 'HEAD_OF_HOME')
ON CONFLICT DO NOTHING;
