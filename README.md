# FRONT-BACK
---
# **AUTH:**

###    POST/auth
        {	
            "login"		: string,
            "password"	: string
        } 
#### return
            {
                "admin"		: boolean,
                "token"		: string,
                "message"	: string
            }

---
## **USER:**

###    POST/user
        {
            "name"		: string,
            "email"		: string,
            "EDV"		: string,
            "phone"		: string,
            "password"	: string
        }
#### return
            {
                "mesage"	: string
            }
    
------------------
### GET/user?page=''&size=''&query=''
#### return
        {
            "numPage"		: int,
            "listObject"	:
            [
                {
                    "id"        : string,
                    "name"      : string,
                    "edv"       : string,
                    "email"     : string,
                    "telefone"  : string,
                    "image"     : string,
                    "student"   : boolean,
                    "admin"     : boolean
                },
                {...}
            ]
        }
    
------------------
###     GET/user/{id}
####    return
        {
            "isOwner"   : boolean,
            "user"      :
            {
                "id"        : string,
                "name"      : string,
                "edv"       : string,
                "email"     : string,
                "telefone"  : string,
                "image"     : string,
                "student"   : boolean,
                "admin"     : boolean
            }
        }
    
------------------
###    PUT/user
        {
            "name"		: string,
            "image"		: MultipartFile,
            "email"		: string,
            "EDV"		: string,
            "phone"		: string,
            "student"	: boolean
        }
#### return
            {
                "message"	: string
            }
------------------
###    PUT/user/password
        {
            "password"		: string,
            "newPassword"	: string
        }
#### return
            {
                "mesage"	: string
            }
------------------
###    DELETE/admin/user/{id}
#### return
        {
            "message"	: string
        }
------------------
###     GET/user/interactions/question/{id}
#### return
    {
        "numPage"       : int
        "listObject"    :
        [
            {
                "idQuestion",
                "title",
                "description"
                "user"          :
                {
                    "id"        : string,
                    "name"      : string,
                    "edv"       : string,
                    "email"     : string,
                    "telefone"  : string,
                    "image"     : string,
                    "student"   : boolean,
                    "admin"     : boolean
                },
                "forum"         :
                {
                    "id"            : int,
                    "name"          : string,
                    "description"   : string
                 },
            },
            {...}
        ]
    }

---------------
###     GET/user/interactions/answer/{id}
#### return
    {
        "numPage"       : int
        "listObject"    :
        [
            {
                "idQuestion",
                "title",
                "description"
                "user"          :
                {
                    "id"        : string,
                    "name"      : string,
                    "edv"       : string,
                    "email"     : string,
                    "telefone"  : string,
                    "image"     : string,
                    "student"   : boolean,
                    "admin"     : boolean
                },
                "forum"         :
                {
                    "id"            : int,
                    "name"          : string,
                    "description"   : string
                 },
                 "answer"       :
                {
                    "id"            : int,
                    "description"   : string,
                    "user"  :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    },
                    "votes"         : 
                    [
                        "id"    : int,
                        "up"    : boolean,
                        "user"  :
                        {
                            "id"        : string,
                            "name"      : string,
                            "edv"       : string,
                            "email"     : string,
                            "telefone"  : string,
                            "image"     : string,
                            "student"   : boolean,
                            "admin"     : boolean
                        }
                    ]
                }
            },
            {...}
        ]
    }			
---------------
###     GET/user/interactions/topic/{id}
####    return
    {   
        "numPage"       : int,
        "listObject"    :
        [
            {
                "id"            : int,
                "name"          : string,
                "description"   : string
            },
            {...}
        ]
    }

---
# **FORUM:**

###    GET/forum/{id}
#### return
        {
            "id"			: int,
            "name"			: string,
            "description"	: string
        }

------------------
###    GET/forum?page=''&size=''&query=''
#### return
            {
                "numPage"		: int,
                "listObject"	:
                [
                    {
                        "id"			: int,	
                        "name"			: string,
                        "description"	: string
                    },
                    {...}
                ]
            }
    

------------------
###    POST/admin/forum
        {
            "name"			: string,
            "description"	: string
        }
#### return
            {
                "message"	: string
            }

------------------
###    DELETE/admin/forum/{id}
#### return
        {
            "message"	: string
        }

---
# **CHAT:**
###    POST/chat/user/{id}
#### return
            {
                "message"	: string
            }

------------------
###    GET/chat?query=''
#### return
        {
            "numPage"		: int,
            "listObject"	:
            [
                "id"	: int,	
                "user"	:
                {
                    "id"        : string,
                    "name"      : string,
                    "edv"       : string,
                    "email"     : string,
                    "telefone"  : string,
                    "image"     : string,
                    "student"   : boolean,
                    "admin"     : boolean
                },
                {...}
            ]
        }

------------------
###    GET/chat/{id}
#### return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id"			: int,
                    "description"	: string,
                    "timestamp"		: date,
                    "user"			:
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    }
                },
                {...}
            ]
        }

------------------
###    POST/chat/message
        {
            "description"	: string,
            "chatId"		: int
        }
#### return
        {
            "message"       : string
        }

---
# **PROJECTS:**
###    POST/project
        {
            "name"			: string,
            "description"	: string,
            "status"		: boolean,
            "startDate"		: date,
            "endDate"		: date,
            "idCategory"	: int
        }
#### return
            {
                "message"	: string
            }

------------------
###    GET/project/me?page=''&size=''&query=''
#### return
        {
            "numPage"       : int
            "listObject"    :
            [
                {
                    "id"			: int,
                    "name"			: string,
                    "description"	: string
                },
                {...}
            ]
        }
------------------
###    GET/project/others?page=''&size=''&query=''
#### return
        {
            "numPage"       : int
            "listObject"    :
            [
                {
                    "id"			: int,
                    "name"			: string,
                    "description"	: string
                },
                {...}
            ]
        }

------------------
###     GET/project/{id}
#### return
        {
            "id"            : int,
            "name"          : string,
            "description"   : string,
            "status"        : booelan,
            "startDate"     : date,
            "endDate"       : date,
            "category"      : string
        }
    
------------------
###    POST/project/feedback
        {
            "isPrivate"     : boolean,
            "idProject"		: int,
            "description	: string
        }
#### return
        {
            "message"   : string
        }
------------------
###     GET/project/{idProject}/feedback/{îdUser}
####    return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id",
                    "description",
                    "isPrivate",
                    "user" :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    }
                },
                {...}
            ]
        }

------------------
###     GET/project/{idProject}/feedback?page=''&size=''&query=''
####    return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id",
                    "description",
                    "isPrivate",
                    "user" :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    }
                },
                {...}
            ]
        }
    
------------------
###    POST/project/user
        {
            "idProject"	: int
            "idUser"	: int
        }
#### return
        {
            "message"       : string
        }
    
------------------
###    DELETE/project/user
        {
            "idProject"	: int
            "idUser"	: int
        }
#### return
            {
                "message"	: string 
            }
------------------
###     GET/project/user/{id}
####    return
        {
            "id"            : int,
            "name"          : string,
            "description"   : string,
            "status"        : booelan,
            "startDate"     : date,
            "endDate"       : date,
            "category"       : string
        }
------------------
###     POST/project/message
        {
            "idProject"     : int,
            "description"   : string,
            "timestamp"     : date
        }
#### return
    {
        "message"   : string
    }
------------------
###     GET/project/{id}/message?page=''&size=''
####    return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id"            : int,
                    "description"   : description,
                    "timestamp"     : date,
                    "user"          :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    }
                },
                {...}
            ]
        }

---
# **QUESTION:**
### POST/question
    {
        "idForum"       : int,
        "title"         : string,
        "description"   : string
    }
#### return
    {
        "message"   : string
    }
------------------
### GET/question?idForum=''&page=''&size=''
    {
        "numPage"       : int,
        "listObject"    :
        [
            "id"            : int,
            "title"         : string,
            "description"   : string,
            answers         : null,
            "user" : 
            {
                "id"        : string,
                "name"      : string,
                "edv"       : string,
                "email"     : string,
                "telefone"  : string,
                "image"     : string,
                "student"   : boolean,
                "admin"     : boolean
            },
            "forum" : 
            {
                "id"            : int
                "name"          : string
                "description"   : string
            },
            {...}
        ]
    }
------------------
### GET/question/{id}/answerPage=''&answerSize=''
#### return
    {
        "id"            : int,
        "title"         : string,
        "description"   : string,
        answers         : 
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id",
                    "description",
                    "user"          :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string,
                        "student"   : boolean,
                        "admin"     : boolean
                    },
                    votes           :
                    [
                        "id"    : int,
                        "up"    : boolean,
                        "user"  :   
                        {
                            "id"        : string,
                            "name"      : string,
                            "edv"       : string,
                            "email"     : string,
                            "telefone"  : string,
                            "image"     : string,
                            "student"   : boolean,
                            "admin"     : boolean
                        }
                    ]
                    
                },
                {...}
            ]
        },
        "user" : 
        {
            "id"        : string,
            "name"      : string,
            "edv"       : string,
            "email"     : string,
            "telefone"  : string,
            "image"     : string,
            "student"   : boolean,
            "admin"     : boolean
        },
        "forum" : 
        {
            "id"            : int
            "name"          : string
            "description"   : string
        }
    }
------------------
### DELETE/question/{id}
#### return
    {
        "message"   : string
    }

---
# **ABILITY:**
### GET/ability?page=''&size=''&query=''
#### return
    {
        "numPage"       : int,
        "listObject"    :
        [
            {
                "id"    : int,
                "name"  : string
            },
            {...}
        ]
    }

------------------
### GET/ability/user/{id}
#### return
    {
        "numPage"       : int,
        "listObject"    :
        [
            {
                "id"    : int,
                "name"  : string
            },
            {...}
        ]
    }

------------------
### POST/admin/ability
    {
        "name"  : string
    }
#### return
    {
        "message"   : string
    }

------------------
### DELETE/admin/ability/{id}
#### return 
    {
        "message"   : string
    }

------------------
### POST/ability/user/{id}
#### return
    {
        "message"   : string
    }
------------------
### DELETE/ability/user/{id}
#### return
    {
        "message"   : string
    }

---
# **ANSWER:**
### POST/answer
    {
        "idQuestion"    : int,
        "description"   : string
    }
#### return
    {
        "message"   : string
    }

------------------
### DELETE/answer/{id}
#### return
    {
        "message"   : string
    }

------------------
### POST/answer/up/{idAnswer}
#### return
    {
        "message"   : string
    }

------------------
### POST/answer/down/{idAnswer}
    {
        "message"   : string
    }


---
# **CAREER:**

### POST/admin/career
    {
        "id"    : null,
        "name"  : string
    }

------------------
### GET/career?page=''&size=''&query=''
#### return
    {
        "numPage"       : int,
        "listObject"    :
        [
            {
                "id"    : int,
                "name"  : string
            },
            {...}
        ]
    }

------------------
### DELETE/admin/career/{id}
#### return
    {
        "message"   : string
    }

------------------
### POST/career/user/{id}
#### return 
    {
        "message"   : string
    }

------------------
### GET/career/user/{id}
#### return
    {
        "numPage"       : int,
        "listObject"    :
        [
            {
                "id"    : int,
                "name"  : string
            },
            {...}
        ]
    }
------------------
### DELETE/career/user/{id}
#### return
    {
        "message"   : string
    }

---
# **TOPIC:**
### POST/topic
    {
        "name"        : string,
        "description"   : string
    }

		



		



		
			












