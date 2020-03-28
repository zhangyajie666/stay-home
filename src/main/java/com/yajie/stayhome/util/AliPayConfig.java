package com.yajie.stayhome.util;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * @author Lenovo
 */
public class AliPayConfig {
    /**
     * 服务网关 沙盒环境
     */
    public static String serverUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 应用id
     */

    public static String appId = "2016101700704491" ;

    public static String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCVo093lZQRREae/JVSNKc6CSQN+MsgpEw183xOC7DzbeZd48UU41zkeC/FQ2y5V9qPIooxclXEnbP3AjbQJPGf6fFxBLAhceLZiVduc+DOB3qpXaeciJN4N4TmKiRL7Ich3SYNYuFp4fenyZHMUaezbu6vTOf1wsayutX3cNi5fYTbzmGxy6smZzKo16iNctwWtqMo3kpFXUYLe4BxRB2l/VU6pX665HgsEj539AFGG2NmUugAvOBydkAWxSbHiTm/WZiI7bM5dLRaZpgFkH3Bvmcc7rHpk9mhhWYx/ENgWiNG7aLeRiD2VjtO6FIHF/ZPIEZb/xfzFSRZFMoxuwcPAgMBAAECggEAKwhXDx3eAgUvhgM/ce+gc8kzLC1zRQTawd0uz0b/cVIWoMOi5rnn+fAyVLlqJ/cC7IzTTxsBXVS4730chElLj2B4xbbCbCvTQBoGIcaDTNzLwq8jR5njRvw4ZPSTkZ4I0o1Fpy3bg1avoPoPysFkx+5MB+/G+V81QysF4235dYYXQm61k2yicuV5Ts1YoUfpZQ00z0t2gLpAk8CbyR4oEMpbnrbs/NkacB9vUnwzK62rQx4k+sPX0PKFuORe6TgJONG5mCSlkRgrO/Nye1mAG3wRzLLF9XRfUuYpJ69S8ypxcTFB/sWtuxGtb5Qo1kSDnrHUlfz6u51RRpYi4oWE+QKBgQDxbf/aI3HZLfUfxve1uf1tQMwhEHAAIJdudsitmgTojkwdN0EOAyhE3YPsWwvLnebV4OP49wBSs9mIuTCn1dn9SegfxgrqjCXHx4BCM1yyDoDbj63RAh+eCiyZEvjv6wllaxesM0ogzsT9Mk05VvwWLXH5EKAkfqA9yDuOl0EaBQKBgQCeqylw22Cnebd3g/dYplQTzWsgywtd7nwRf7zCU9hanBSkKR5iBh8tIxeEaX6v/N7mGWpQbglKRErGLdWWreRk8DGxkUN/zq97TZN8ezP6yve18po7inJArmCOc3PP9Dd0jk7ISAXJg2kVkBS6JN035wli2muIXx3xgGFbG3ElAwKBgQCD9bjLlWYL6/e4N7t7Q76GMc2hIOfm2emLQjer06xtJCHsMiBHtWgUQ/Nwg88tqw/ZxUM1+GPf0FfNSFOU2Ol+bSAIBeo/ivy3do7fIE3BMPYzjBPYUs3PRwZbYKhaEmCfY2qb/6i7x+iZ8B2PgsDkqlBKb1DowaF6Q3ByHrBBtQKBgEAaLHLg3mqq3PlXAl35TKx3aBlmjqATUmKLdcoYr6bjud8ckZ7OlFfa/oxLRph5E+BoUjBOVEiB0KfCKrFmbYhuFdWEN33yFak+DSDUcYqX533FlpQJeeEx0eabNCXGp4TaXW/C0Tu5cIH6pLUTYaBNIdijH6CHtQQ1dp+lNY/bAoGATuqNxAFkvJQ+DqKEsK3vGSpSF+rgSsXnp24uUi5PF1eRt7oMMUaPpOWUA2OPpOl2PxPCH6pozrS0DOmyMCWdEahcR9rldTv/UM5QeI6liRW2pW2O5rlqr+PhsXSCJNanfdByjxKuMrXykO94uFS6fT8F2AR4lFjsmDCT3DXbEcQ=" ;

    public static String format = "json";

    public static String charset = "utf-8" ;

    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlaNPd5WUEURGnvyVUjSnOgkkDfjLIKRMNfN8Tguw823mXePFFONc5HgvxUNsuVfajyKKMXJVxJ2z9wI20CTxn+nxcQSwIXHi2YlXbnPgzgd6qV2nnIiTeDeE5iokS+yHId0mDWLhaeH3p8mRzFGns27ur0zn9cLGsrrV93DYuX2E285hscurJmcyqNeojXLcFrajKN5KRV1GC3uAcUQdpf1VOqV+uuR4LBI+d/QBRhtjZlLoALzgcnZAFsUmx4k5v1mYiO2zOXS0WmaYBZB9wb5nHO6x6ZPZoYVmMfxDYFojRu2i3kYg9lY7TuhSBxf2TyBGW/8X8xUkWRTKMbsHDwIDAQAB";

    public static String signType = "RSA2";

    public static String return_url = "http://zhangyajie.top:8800/index";

    public static AlipayClient getAlipayClient() {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient =
                new DefaultAlipayClient(AliPayConfig.serverUrl,
                        AliPayConfig.appId, AliPayConfig.privateKey,
                        AliPayConfig.format, AliPayConfig.charset,
                        AliPayConfig.publicKey, AliPayConfig.signType);
        return alipayClient;
    }
}
