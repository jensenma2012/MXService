[@override name="title"]
	编辑歌手
[/@override]

[@override name="subtitle"]
	编辑歌手
[/@override]

[@override name="action"]
	update
[/@override]

[@override name="form"]
	<tr>
		<th>歌手名:</th>
		<td>
			${artist.name}
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>别名:</th>
		<td>
			<input type="text" name="alias" class="text" value="${artist.alias}" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th>歌手描述:</th>
		<td>
			<textarea name="description" class="textarea" maxlength="255">${artist.description}</textarea>
		</td>
	</tr>
[/@override]

[@override name="rules"]
	alias: {
		required:true,
		maxlength:64
	},
	description: {
		maxlength:255
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]