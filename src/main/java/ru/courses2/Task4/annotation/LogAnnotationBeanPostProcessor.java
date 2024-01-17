package ru.courses2.Task4.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.courses2.Task4.logging.Logging;

import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogAnnotationBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogTransformation.class)) {
            //сохраним список всех бинов
            map.put(s, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        Class beanClass = map.get(s);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        String text = "";
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        text = text + "\nДата и время начала операции: " + formatter.format(new Date());
                        text = text + "\nИмя класса: " + beanClass.getName();
                        text = text + "\nВходные параметры: " + Arrays.toString(args);
                        Object result = method.invoke(bean, args);
                        text = text + "\nВозвращаемые данные: " + result.toString();
                        text = text + "\n----------------------------------------------";

                        //сохраним лог
                        new Logging(bean.getClass().getAnnotation(LogTransformation.class).value(), text).saveLog();

                        return result;
                    });
        }
        return bean;
    }
}
