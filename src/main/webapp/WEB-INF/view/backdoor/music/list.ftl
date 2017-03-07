[@override name="title"]
	歌曲列表
[/@override]

[@override name="subtitle"]
	歌曲列表
[/@override]

[@override name="addbutton"]
	[@security.authorize access="hasRole('ROLE_MUSIC_ADD')"]
		<a href="add" class="iconButton">
			<span class="addIcon">&nbsp;</span>添加
		</a>
	[/@security.authorize]
[/@override]

[@override name="deletebutton"]
	[@security.authorize access="hasRole('ROLE_MUSIC_DELETE')"]
		<a href="javascript:;" id="deleteButton" class="iconButton disabled">
			<span class="deleteIcon">&nbsp;</span>删除
		</a>
	[/@security.authorize]
[/@override]

[@override name="extrabutton"]
	[@security.authorize access="hasRole('ROLE_MUSIC_REFRESH')"]
		<a href="javascript:;" id="refreshConfigButton" class="iconButton">
			<span class="upIcon">&nbsp;</span>同步
		</a>
	[/@security.authorize]
[/@override]

[@override name="columntitle"]
	<th>
		<a href="javascript:" class="sort" name="title">歌曲标题</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="description">歌曲描述</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="album.title">专辑</a>
	</th>
	<th>
		<a href="javascript:" class="sort" name="createDate">创建时间</a>
	</th>
	[@security.authorize access="hasRole('ROLE_MUSIC_EDIT')"]
		<th>	
			<span>操作</span>
		</th>
	[/@security.authorize]
[/@override]

[@override name="columnvalue"]
	<td>
		${list.title}
	</td>
	<td>
		${list.description}
	</td>
	<td>
		${list.album.title}-${list.album.artist.name}
	</td>
	<td>
		${(list.createDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
	</td>
	[@security.authorize access="hasRole('ROLE_MUSIC_EDIT')"]
		<td>
			<a href="edit?id=${list.id}">[编辑]</a>
		</td>
	[/@security.authorize]
[/@override]

[@extends name="backdoor/base/table.ftl" /]