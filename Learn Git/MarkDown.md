# Syntax Examples
Here's an overview of Markdown syntax that you can use anywhere on GitHub.com or your own text files.

### Text
It's very easy to make some words **bold** and other words *italic* with Markdown: `**bold**` & `*italic*`

You can even [link to my LinkedIn](https://www.linkedin.com/in/hongyuan-ning/)! `[Alt Text](url)`

### Lists
Sometimes you want numbered lists:

1. One `1. One`
2. Two `2. Two`
3. Three `3. Three`

Sometimes you want bullet points:

* Start a line with a star	`* Start a line with a star`
* Profit!	`* Profit!`

Alternatively,

- Dashes work just as well
- And if you have sub points, put two spaces before the dash or star:
  - Like this
  - And this

```
- Dashes work just as well
- And if you have sub points, put two spaces before the dash or star:
  - Like this
  - And this
```

### Images

It you want to embed images, this is how to do it: `![Alt Text](url)`

![Imge of myself](https://github.com/weltond/DataStructure/blob/master/Learn%20Git/myself.jpg)

### Headers & Quotes
# Structured Documents

Sometimes it's useful to have different levels of headings to structure your documents. Start lines with a `#` to create headings.
Multiple `##` in a row denote smaller heading sizes.

### This is a third-tier heading

You can use one `#` all the way up to `######` six for different heading sizes.

If you'd like to quote someone, use the > character before the line:

> Stay Hungry. Stay Foolish!
>  -- Steve Jobs


### Code
There are many different ways to style code with GitHub's markdown.
If you have inline code blocks, wrap them in backticks: `boolean isTrue = true`. If you've got a longer block of code, you can indent with four spaces:

	if (isHandsome) {
		return true;
	}
	
GitHub also supports something called code fencing, which allows for multiple lines without indentation:

```
if (isHandsome) {
  return true;
}
```

And if you'd like to use syntax hightlighting, include the language:

```java
if (isHandome) {
  return true;
}
```

### Tables
You can create tables by assembling a list of words and dividing them with hyphens `-` (for the first row), and then separating each column with a pipe `|`:

```
First Header | Second Header
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column
```

Would become:

First Header | Second Header
------------ | -------------
Content from cell 1 | Content from cell 2
Content in the first column | Content in the second column

### Extras
#### @mentions
GitHub supports many extras in Markdown that help you reference and link to people. If you ever want to direct a comment at someone,
you can prefix their name with an @ symbol: Hey @weltond - Love your face!

#### Task Lists
But I have to admit, tasks lists are my favorite:

- [x] Write code today
- [ ] Meet a girl today

When you include a task list in the first comment of an Issue, you will see a helpful progress bar in your list of issues. It works in Pull Requests, too!

#### Strikethrough
Any word wrapped with two tildes like ~~this~~ will appear crossed out.	 `~~this~~`

#### Emoji
And of course emoji! :sparkles: ; :camel: ; :boom:
