[@override name="title"]
	微信用户列表
[/@override]

[@override name="subtitle"]
	微信用户列表
[/@override]

[@override name="columntitle"]
	<th>
		<a href="javascript:" class="sort" name="username">微信id</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="alias">用户别名</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="createDate">创建时间</a>
	</th>
	[@security.authorize access="hasRole('ROLE_WECHATUSER_EDIT')"]
		<th>	
			<span>操作</span>
		</th>
	[/@security.authorize]
[/@override]

[@override name="columnvalue"]
	<td>
		${list.username}
	</td>
	<td>
		${list.alias}
	</td>
	<td>
		${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
	</td>
	[@security.authorize access="hasRole('ROLE_WECHATUSER_EDIT')"]
		<td>
			<a href="edit?id=${list.id}">[编辑]</a>
		</td>
	[/@security.authorize]
[/@override]

[@extends name="backdoor/base/table.ftl" /]