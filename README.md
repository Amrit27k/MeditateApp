# MeditateApp

This is an Android repository that we will use to maintain the Meditate App project.

---

### Prerequisites

Make sure you have installed

1. [git](https://git-scm.com/downloads)

---

Local Development Environment Configuration
-------------------------------------------

1. Fork the project, clone your fork, and configure the remotes::

    # Clone your fork of the repo into the current directory
    git clone https://github.com/<your-username>/MeditateApp.git
    # Navigate to the newly cloned directory
    cd MeditateApp
    # Assign the original repo to a remote called "upstream"
    git remote add upstream https://github.com/Amrit27k/MeditateApp

2. If you cloned a while ago, get the latest changes from upstream::
 
    git checkout master
    git pull upstream master

3. Create the virtual environment for application::
  
    # navigate to the MeditateApp
    cd MeditateApp

    # create the virtual environment
    python -m venv App_venv

    # activate the virtual environment
    source App_venv/bin/activate
    
4. Create a new topic branch (off the main project development branch) to contain your feature, change, or fix::

    git checkout -b <topic-branch-name>
    
5. Add the changed elements 
   git add .
5. Commit your changes
    git commit -m "Comment"
6. Push your topic branch up to your fork::
    
    git push origin <topic-branch-name>

7. Open a Pull Request with a clear title and description.
8. After your Pull Request is away, you might want to get yourself back onto master and delete the topic branch::

    git checkout master
    git branch -D <topic-branch-name>
