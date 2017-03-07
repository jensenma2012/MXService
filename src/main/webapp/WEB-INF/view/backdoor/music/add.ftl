[@override name="title"]
	添加歌曲
[/@override]

[@override name="subtitle"]
	添加歌曲
[/@override]

[@override name="action"]
	save
[/@override]

[@override name="form"]
	<tr>
		<th><span class="requiredField">*</span>歌曲标题:</th>
		<td>
			<input type="text" name="title" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>歌曲描述:</th>
		<td>
			<input type="text" name="description" class="text" maxlength="64" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>歌曲:</th>
		<td>
			<input type="file" name="file" class="text" />
		</td>
	</tr>
	<tr>
		<th><span class="requiredField">*</span>专辑:</th>
		<td>
			<select name="album.id">
				<option value="">--请选择--</option>
				[#list albums as album]
					<option value="${album.id!}">${album.title!}-${album.artist.name}</option>
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
	file: {
		required:true,
		filetype:["mp3"]
	},
	"album.id": {
		required:true
	}
[/@override]

[@extends name="backdoor/base/form.ftl" /]