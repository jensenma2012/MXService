[@override name="title"]
	添加歌手
[/@override]

[@override name="subtitle"]
	添加歌手
[/@override]

[@override name="action"]
	save
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>歌手名:</th>
		<td>
			<input type="text" name="name" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>别名:</th>
		<td>
			<input type="text" name="alias" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th>歌手描述:</th>
		<td>
			<textarea name="description" class="textarea" maxlength="255"></textarea>
		</td>
	</tr>
[/@override]

[@override name="rules"]
	name: {
		required:true,
		maxlength:64
	},
	alias: {
		required:true,
		maxlength:64
	},
	description: {
		maxlength:255
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]