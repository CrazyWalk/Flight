package cn.luyinbros.valleyframework.flight;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class RouteRequest {
    private final String path;
    private final Bundle bundle;


    private RouteRequest(Builder builder) {
        this.path = builder.path;
        this.bundle=builder.bundle;
    }


    public String path() {
        return path;
    }


    public Bundle extra() {
        return bundle;
    }



    public static class Builder {
        private String path;
        private Bundle bundle;


        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder putBoolean(String key, boolean value) {
            bundle.putBoolean(key, value);
            return this;
        }

        public Builder putByte(String key, byte value) {
            bundle.putByte(key, value);
            return this;
        }

        public Builder putShort(String key, short value) {
            bundle.putShort(key, value);
            return this;
        }

        public Builder putChar(String key, char value) {
            bundle.putChar(key, value);
            return this;
        }

        public Builder putInt(String key, int value) {
            bundle.putInt(key, value);
            return this;
        }

        public Builder putLong(String key, long value) {
            bundle.putLong(key, value);
            return this;
        }

        public Builder putFloat(String key, float value) {
            bundle.putFloat(key, value);
            return this;
        }

        public Builder putDouble(String key, double value) {
            bundle.putDouble(key, value);
            return this;
        }

        public Builder putString(String key, String value) {
            bundle.putString(key, value);
            return this;
        }

        public Builder putCharSequence(String key, CharSequence value) {
            bundle.putCharSequence(key, value);
            return this;
        }

        public Builder putParcelable(String key, Parcelable value) {
            bundle.putParcelable(key, value);
            return this;
        }

        public Builder putSerializable(String key, Serializable value) {
            bundle.putSerializable(key, value);
            return this;
        }


        public Builder putIntegerArrayList(String key, ArrayList<Integer> value) {
            bundle.putIntegerArrayList(key, value);
            return this;
        }

        public Builder putStringArrayList(String key, ArrayList<String> value) {
            bundle.putStringArrayList(key, value);
            return this;
        }

        public Builder putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
            bundle.putCharSequenceArrayList(key, value);
            return this;
        }

        public Builder putBooleanArray(String key, boolean[] value) {
            bundle.putBooleanArray(key, value);
            return this;
        }

        public Builder putByteArray(String key, byte[] value) {
            bundle.putByteArray(key, value);
            return this;
        }

        public Builder putShortArray(String key, short[] value) {
            bundle.putShortArray(key, value);
            return this;
        }

        public Builder putCharArray(String key, char[] value) {
            bundle.putCharArray(key, value);
            return this;
        }

        public Builder putIntArray(String key, int[] value) {
            bundle.putIntArray(key, value);
            return this;
        }

        public Builder putLongArray(String key, long[] value) {
            bundle.putLongArray(key, value);
            return this;
        }

        public Builder putFloatArray(String key, float[] value) {
            bundle.putFloatArray(key, value);
            return this;
        }

        public Builder putDoubleArray(String key, double[] value) {
            bundle.putDoubleArray(key, value);
            return this;
        }

        public Builder putStringArray(String key, String[] value) {
            bundle.putStringArray(key, value);
            return this;
        }

        public Builder putCharSequenceArray(String key, CharSequence[] value) {
            bundle.putCharSequenceArray(key, value);
            return this;
        }

        public Builder putParcelableArray(String key, Parcelable[] value) {
            bundle.putParcelableArray(key, value);
            return this;
        }

        public Builder putBundle(String key, Bundle value) {
            bundle.putBundle(key, value);
            return this;
        }


        public RouteRequest build() {
            return new RouteRequest(this);
        }
    }
}
