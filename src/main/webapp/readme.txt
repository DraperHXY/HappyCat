��Ŀ����spring+springMVC+mybatis+shiro�ܹ�
����
1.shiroʹ��ע��
	shiro���ö�����session��У����ƣ��Զ���ShiroDbRealm��ɵ�¼����֤��Ȩ�޵�У�顣
	 doGetAuthenticationInfo()������currentUser.login()��currentUser.isAuthenticated()ʱ����
	 doGetAuthorizationInfo()��Ȩ����֤������ֻ����currentUser.isPrimetted()��������ʱ�Ż�ִ��
	 ����������������ֻ���ڳ��ε��õ�ʱ��ִ�У�֮���ε���ֱ�Ӳ�ѯ���棬�������������ݿ��ѹ����
	 ��jspҳ����ͨ��ʹ�����±�ǩ���м��Ȩ��
	 <shiro:hasPermission name=��users:manage��>
        <a href=��manageUsers.jsp��>
            Click here to manage users
        </a>
    </shiro:hasPermission>
    <shiro:lacksPermission name=��users:manage��>
        No user management for you!
    </shiro:lacksPermission>
    
	ʹ���û��Զ���shiro��������ÿ���û������url����Ȩ����֤�����û��Ƿ��з��ʸ�url��Ȩ�ޣ�
	�ο��̳�
	http://blog.csdn.net/wxwzy738/article/details/10992161

2.ʹ��aop����ķ�ʽ��controller������־��¼��ʹ��log4j


3.��ҳ���ʹ��pagehelper