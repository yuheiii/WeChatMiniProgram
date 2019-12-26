package com.yuxi.projectdemo.wechat.frontendObject;

import lombok.Data;

/** the outermost object that http request returns
 *
 * e.g.: JSON object
 *
 * {
 *  "code": 0,
 *  "msg": "succeed",
 *  "data": [
 *      {
 *          "name": "category1",
 *          type": 1,
 *          "foods": [
 *              {
 *                  "id": "123456",
 *                  "name": "product1",
 *                  "price": 1.3,
 *                  "description": "made of xxx",
 *                  "icon": "http://xxx.com",
 *               }
 *          ]
 *       },
 *       {
 *       category2 xxxxx
 *       }
 *  ]
 *}
 *
 *
 * */

@Data
public class ResultFrontendObject<T> {

    /** error code */
    private Integer code;

    /** notice message */
    private String msg;

    /** generics - content returned*/
    private T data;



}



