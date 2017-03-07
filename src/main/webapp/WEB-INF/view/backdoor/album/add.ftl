[@override name="title"]
	添加专辑
[/@override]

[@override name="subtitle"]
	添加专辑
[/@override]

[@override name="action"]
	save
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>专辑标题:</th>
		<td>
			<input type="text" name="title" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th>专辑描述:</th>
		<td>
			<textarea name="description" class="textarea" maxlength="255"></textarea>
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>歌手:</th>
		<td>
			<select name="artist.id">
				<option value="">--请选择--</option>
				[#list artists as artist]
					<option value="${artist.id!}">${artist.name!}</option>
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
		maxlength:255
	},
	"artist.id": {
		required:true
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]