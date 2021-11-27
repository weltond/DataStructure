Create and commit:
1. git add .
2. git commit -m "your update"

Rollback
3. git log [--pretty=oneline]
4. git reset --hard HEAD^
   1) prev version: HEAD^
   2) one before prev version: HEAD^^
   3) the last 100th version: HEAD~100
   4) use version SHA1 number
5. git reflog ------> to find your commit logs
6. 1) working directory: your local drive
   2) Repository(stage)
7. 1) git checkout -- <file>     --------> to discard changes in working directory if not add yet
   2) git reset HEAD <file> 	 --------> to unstage if add but not commit
   3) see No.4			 --------> to rollback version if add and commit
8. git rm <file>

Remote Repository - GitHub
1. 1) Type command: ssh-keygen -t rsa -C youremail@example.com     
	to generate two files: id_rsa and id_rsa.pub under /user/.ssh/
   2) Copy and add id_rsa.pub content into your github "SSH keys" to make GitHub knows that its your public key 
2. 1) create a new repo in your GitHub
   2) Type command: git remote add origin https://github.com/weltond/DataStructure.git
   3) Type command: git push -u origin master 
   	for the first time. 
      After that, type command: git push origin master
3. clone from GitHub:
   Type command: git clone https://github.com/weltond/DataStructure.git

Branch
1. Create and switch to branch 'dev': git checkout -b dev
2. Check current branches: git branch
3. Switch branch (master here): git checkout master
4. Merge branch('dev') to current branch(master): git merge dev
5. Check log: git log --graph --pretty=oneline --abbrev-commit
5. Delete branch 'dev': git branch -d dev
6. Save work directory and create a new branch to fix other bugs: 
   1) git stash (on branch dev)
   	1.1) git checkout master; git checkout -b issue1; git commit -m ""; git checkout master; git merge --no-ff -m "merge bug fix issue1" issue1; git branch -d issue1
   	1.2) git checkout dev; git stash list
   2) git stash pop
7. Force delete an un-merged branch: git branch -D <name>
8. git remote -v
9. git checkout -b dev origin/dev
10. Set connection between local branch dev and remote origin/dev branch: git branch --set-upstream-to=origin/dev dev
How to coordinate in a team:
1) Try to push your code: 
	git push origin <branch-name>
2) If fail, because remote branch is ahead of your work directory, type command:
	git pull
3) If step 2) also fails, it means your work directory doesn't create a connection with remote branch yet. Type command:
	git branch --set-upstream-to=origin/branch-name branch-name
4) Fix conflicts if there is any. 
5) Push to remote: 
	git push origin branch-name
11. Teamate: Teammates can reach your branch, by doing:
git fetch
git checkout origin/your_branch

Tag:
1. git tag <tag-name> [version number]
2. git show <tag-name>
3. git tag -a <tag-name> -m "blablabla..."
4. git tag -d <tag-name>  	to delete a tag
5. git push origin <tag-name>/--tags	to push tag/tags to remote repo
6. git push origin :refs/tags/<tag-name>	to deelte a remote tag

Alias:
1. git config --global alias.st status
   git config --global alias.co checkout
   git config --global alias.ci commit
   git config --global alias.br branch
   git config --global alias.unstage 'reset HEAD'
   git config --global alias.last 'log -1'
   git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
2. Git Configuration File: cat .git/config

# Undo a git push
https://stackoverflow.com/questions/1270514/undoing-a-git-push
