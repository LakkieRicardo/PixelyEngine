/*

This file contains utilities, such as vector, vector math, and logger systems.

*/

var Mathif = Java.type("net.lakkie.pixely.math.Mathif");

var logger = {
    info: function (msg) {
        ntv_Info(msg);
    },
    warning: function (msg) {
        ntv_Warning(msg);
    },
    severe: function (msg) {
        ntv_Severe(msg);
    }
}

/*
 * This class is essentially a JavaScript wrapper for the Vector2f class
 */
function Vector2(x, y) {
    this.x = x;
    this.y = y;
    this.abs = function () {
        return new Vector2(Math.abs(this.x), Math.abs(this.y));
    }
    this.length = function () {
        return this.x + this.y;
    }
    this.normalize = function () {
        var len = this.length();
        var result = new Vector2(this.x, this.y);
        result.x /= len;
        result.y /= len;
        return result;
    }
    this.ceil = function () {
        return new Vector2(Math.ceil(this.x), Math.ceil(this.y));
    }
    this.floor = function () {
        return new Vector2(Math.floor(this.x), Math.floor(this.y));
    }
    this.scalar = function (scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }
    this.add = function (other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    this.subtract = function (other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }
    this.multiply = function (other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }
    this.divide = function (other) {
        return new Vector2(this.x / other.x, this.y / other.y);
    }
    this.modulus = function (other) {
        return new Vector2(this.x % other.x, this.y % other.y);
    }
}

/*
 * This class is a JS wrapper for Vector4
 */
function Vector4(x, y, w, h) {
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.add = function (other) {
        return new Vector4(this.x + other.x, this.y + other.y, this.w + other.w, this.h + other.h);
    }
    this.subtract = function (other) {
        return new Vector4(this.x - other.x, this.y - other.y, this.w - other.w, this.h - other.h);
    }
    this.multiply = function (other) {
        return new Vector4(this.x * other.x, this.y * other.y, this.w * other.w, this.h * other.h);
    }
    this.divide = function (other) {
        return new Vector4(this.x / other.x, this.y / other.y, this.w / other.w, this.h / other.h);
    }
    this.modulus = function (other) {
        return new Vector4(this.x % other.x, this.y % other.y, this.w % other.w, this.h % other.h);
    }
    this.length = function () {
        return this.x + this.y + this.w + this.h;
    }
    this.abs = function () {
        return new Vector4(Math.abs(this.x), Math.abs(this.y), Math.abs(this.w), Math.abs(this.h));
    }
    this.scalar = function (scalar) {
    	return new Vector4(this.x * scalar, this.y * scalar, this.w * scalar, this.h * scalar);
    }
}