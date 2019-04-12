import tensorflow as tf

def rename_var(ckpt_path, new_ckpt_path):
    with tf.Session() as sess:
        for var_name, _ in tf.contrib.framework.list_variables(ckpt_path):
            print(var_name)
            var = tf.contrib.framework.load_variable(ckpt_path, var_name)
            new_var_name = var_name.replace('InceptionV3', 'IV')
            var = tf.Variable(var, name=new_var_name)

        saver = tf.train.Saver()
        sess.run(tf.global_variables_initializer())
        saver.save(sess, new_ckpt_path)

ckpt_path = './inception_v3.ckpt'
new_ckpt_path = './iv.ckpt'
rename_var(ckpt_path, new_ckpt_path)

def get_var_value(ckpt_path, var_name):
    from tensorflow.python import pywrap_tensorflow
    reader = pywrap_tensorflow.NewCheckpointReader(ckpt_path)
    print(reader.get_tensor(var_name))

get_var_value(ckpt_path, 'InceptionV3/Conv2d_2a_3x3/BatchNorm/beta')
get_var_value(new_ckpt_path, 'IV/Conv2d_2a_3x3/BatchNorm/beta')

