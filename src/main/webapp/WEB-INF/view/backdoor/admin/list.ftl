[@override name="title"]
	管理员列表
[/@override]

[@override name="subtitle"]
	管理员列表
[/@override]

[@override name="addbutton"]
	[@security.authorize access="hasRole('ROLE_ADMIN_ADD')"]
		<a href="add" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加
		</a>
	[/@security.authorize]
[/@override]

[@override name="columntitle"]
	<th>
		<a href="javascript:" class="sort" name="username">用户名</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="role.name">权限</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="enabled">是否启用</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="createDate">创建时间</a>
	</th>
	[@security.authorize access="hasRole('ROLE_ADMIN_EDIT')"]
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
		${list.role.name}
	</td>
	<td>
		<span class="${list.enabled?then("trueIcon","falseIcon")}">&nbsp;</span>
	</td>
	<td>
		${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
	</td>
	[@security.authorize access="hasRole('ROLE_ADMIN_EDIT')"]
		<td>
			<a href="edit?id=${list.id}">[编辑]</a>
		</td>
	[/@security.authorize]
[/@override]

[@extends name="backdoor/base/table.ftl" /]