[@override name="title"]
	编辑歌曲
[/@override]

[@override name="subtitle"]
	编辑歌曲
[/@override]

[@override name="action"]
	update
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>歌曲标题:</th>
		<td>
			<input type="text" name="title" class="text" value="${music.title}" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>歌曲描述:</th>
		<td>
			<input type="text" name="description" class="text" value="${music.description}" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th>歌曲:</th>
		<td>
			<audio controls src="${musicUrl}${music.filename}">您的浏览器不支持audio标签</audio>
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>专辑:</th>
		<td>
			<select name="album.id">
				<option value="">--请选择--</option>
				[#list albums as album]
					<option value="${album.id!}" [#if music.album.id?? && music.album.id==album.id]selected[/#if]>${album.title!}-${album.artist.name}</option>
				[/#list]
			</select>
		</td>
	</tr>
[/@override]

[@override name="rules"]
	title: {
		required:true,
		maxlength:64
	},
	description: {
		required:true,
		maxlength:64
	},
	"album.id": {
		required:true
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]