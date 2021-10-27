@org.hibernate.annotations.GenericGenerator(
        name = "ID_USER_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
        @org.hibernate.annotations.Parameter(
                name = "initial_value",
                value = "2"
        )
})
@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
        @org.hibernate.annotations.Parameter(
                name = "initial_value",
                value = "1"
        )
})

package com.myproject.learneng.hibernateconfig;
