[@override name="title"]
	歌手列表
[/@override]

[@override name="subtitle"]
	歌手列表
[/@override]

[@override name="addbutton"]
	[@security.authorize access="hasRole('ROLE_ARTIST_ADD')"]
		<a href="add" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加
		</a>
	[/@security.authorize]
[/@override]

[@override name="columntitle"]
	<th>
		<a href="javascript:" class="sort" name="name">歌手名</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="alias">别名</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="description">歌手描述</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="createDate">创建时间</a>
	</th>
	[@security.authorize access="hasRole('ROLE_ARTIST_EDIT')"]
		<th>	
			<span>操作</span>
		</th>
	[/@security.authorize]
[/@override]

[@override name="columnvalue"]
	<td>
		${list.name}
	</td>
	<td>
		${list.alias}
	</td>
	<td>
		${list.description}
	</td>
	<td>
		${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
	</td>
	[@security.authorize access="hasRole('ROLE_ARTIST_EDIT')"]
		<td>
			<a href="edit?id=${list.id}">[编辑]</a>
		</td>
	[/@security.authorize]
[/@override]

[@extends name="backdoor/base/table.ftl" /]